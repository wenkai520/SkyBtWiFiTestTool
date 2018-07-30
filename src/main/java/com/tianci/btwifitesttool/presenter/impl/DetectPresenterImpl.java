package com.tianci.btwifitesttool.presenter.impl;

import android.bluetooth.BluetoothDevice;
import android.net.wifi.ScanResult;
import android.os.Looper;
import android.os.Message;

import com.tianci.btwifitesttool.api.BtApi;
import com.tianci.btwifitesttool.api.WifiApi;
import com.tianci.btwifitesttool.common.AndroidBroadcast;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.common.ConfigConst.WifiCheckResult;
import com.tianci.btwifitesttool.common.ConfigConst.BtCheckResult;
import com.tianci.btwifitesttool.model.config.BtConfigItemData;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.model.help.BtConfigDataHelper;
import com.tianci.btwifitesttool.model.help.WifiConfigDataHelper;
import com.tianci.btwifitesttool.presenter.IDetectPresenter;
import com.tianci.btwifitesttool.utils.Calculate;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.view.IDetectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class DetectPresenterImpl implements IDetectPresenter {

    private ScheduledThreadPoolExecutor executorService;
    private ScheduledThreadPoolExecutor checkExecutorService;
    private IDetectView detectView;
    private IsNewModuleInsertRunnable runnable = null;
    private CheckBtRunnable checkBtRunnable = null;
    private CheckWifiRunnable checkWifiRunnable = null;
    private UiHandler uiHandler = null;
    private String preModuleAddr = ConfigConst.MAC_ADDR_DEFAULT;
    private String currentMouleAddr = null;

    /**
     * bt相关变量定义
     */
    private List<String> btDeviceNameList = new ArrayList<String>();
    private BtCheckResult btCheckResult = BtCheckResult.IDLE;
    private List<String> btResultList = new ArrayList<String>();
    private String btMac = null;
    private List<String> btFindConfigNameList = new ArrayList<String>();
    private long btStartAddr = 0L;
    private long btEndAddr = 0L;
    private BluetoothDevice device = null;
    /**
     * wifi相关变量定义
     */
    private List<WifiConfigSubItem> wifiResultData = new ArrayList<WifiConfigSubItem>();;
    private String wifiAddr = null;
    private WifiCheckResult wifiCheckResult = WifiCheckResult.IDLE;
    private List<String> ssidConfigList = new ArrayList<String>();
    private int threshold = 100;
    private Map<String, Integer> wifiFindResults = new HashMap<String, Integer>(ConfigConst.WIFI_TOTOL);
    private long wifiStartAddr = 0L;
    private long wifiEndAddr = 0L;
    private Map<String, Integer> standardVauleConfigs = new HashMap<String, Integer>(ConfigConst.WIFI_TOTOL);


    public DetectPresenterImpl(IDetectView view) {
        detectView = view;
        initWifiConfigData();
        initBtConfigData();

        uiHandler = new UiHandler(Looper.getMainLooper());
        runnable = new IsNewModuleInsertRunnable();
        executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private AtomicInteger atoInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("scan-loop-thread "+ atoInteger.getAndIncrement());
                return t;
            }
        });
        checkBtRunnable = new CheckBtRunnable();
        checkWifiRunnable = new CheckWifiRunnable();
        checkExecutorService = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            private AtomicInteger atoInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("check-thread "+ atoInteger.getAndIncrement());
                return t;
            }
        });
    }

    private void initBtConfigData() {
        BtConfigItemData btConfigItems = (BtConfigItemData )BtConfigDataHelper.getInstance().getData();
        if (btConfigItems == null) {
            L.e("config file btConfigItems is null !!!");
            return;
        }
        try {
            btStartAddr = Long.parseLong(btConfigItems.getMacConfigRangeItem().getStartAddress().replace(":", ""), 16);
            btEndAddr = Long.parseLong(btConfigItems.getMacConfigRangeItem().getEndAddress().replace(":", ""), 16);
        } catch (Exception e) {
            L.e("bt config format illegal !!!" + e.getMessage());
        }
        List<String> btNameConfigList = btConfigItems.getBtNameConfigList();
        if (btNameConfigList == null || btNameConfigList.isEmpty()) {
            L.e("btNameConfigList is null !!!");
            return;
        }
        for (String btName : btNameConfigList) {
            if (btName != null && !"".equals(btName)) {
                btFindConfigNameList.add(btName);
            }
        }
    }

    private void initWifiConfigData() {
        WifiConfigItemData wifiConfigData = (WifiConfigItemData )WifiConfigDataHelper.getInstance().getData();
        if (wifiConfigData == null) {
            L.e("config file wifiConfigData is null !!!");
            return;
        }
        try {
            threshold = Integer.parseInt(wifiConfigData.getRssiConfigPercentThreshold());
            wifiStartAddr = Long.parseLong(wifiConfigData.getMacConfigRangeItem().getStartAddress().replace(":", ""), 16);
            wifiEndAddr = Long.parseLong(wifiConfigData.getMacConfigRangeItem().getEndAddress().replace(":", ""), 16);
        } catch (Exception e) {
            L.e("wifi config format illegal !!!" + e.getMessage());
        }
        List<WifiConfigSubItem> configItems = wifiConfigData.getWifiConfigSubItemList();
        if (configItems == null || configItems.isEmpty()) {
            L.e("configItems ssid is null !!!");
            return;
        }

        for (WifiConfigSubItem item : configItems) {
            String configSsidName = item.getConfigSsidName();
            if (configSsidName != null && !"".equals(configSsidName)) {
                ssidConfigList.add(configSsidName);
                standardVauleConfigs.put(configSsidName, Integer.parseInt(item.getConfigRssiStandradValue()));
            }
        }
        for (String key : standardVauleConfigs.keySet()) {
            L.d("standardVauleConfigs[" + key + "," + standardVauleConfigs.get(key) + "]");
        }
    }

    @Override
    public void addBroadcast() {
        AndroidBroadcast.getInstance().addBroadcast(this);
    }

    @Override
    public void startDetect() {
        executorService.scheduleWithFixedDelay(runnable, 1, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stopDetect() {
        BtApi.getInstance().cancelDiscovery();
        executorService.shutdownNow();
        checkExecutorService.shutdownNow();
    }

    private boolean isNewModuleInsert() {
        String currentMouleAddr = WifiApi.getInstance().getMacAddress();
        L.d("preModuleAddr=" + preModuleAddr + " currentMouleAddr=" + currentMouleAddr);
        //没有模块接入
        if (currentMouleAddr == null) {
            uiHandler.sendEmptyMessage(MSG_RESET_DEFAULT);
            preModuleAddr = null;
            return false;
        }
        //有模块接入
        if (currentMouleAddr.equals(preModuleAddr)) {
            return false;
        }
        L.d("isNewModuleInsert......");
        preModuleAddr = currentMouleAddr;
        return true;
    }

    private void resetDetectView() {
        wifiResultData.clear();
        detectView.refreshWifiView(wifiResultData, "", WifiCheckResult.IDLE);
        btResultList.clear();
        detectView.refreshBtView(btResultList, "", BtCheckResult.IDLE);
    }

    private void refreshWifiView() {
        detectView.refreshWifiView(wifiResultData, wifiAddr, wifiCheckResult);
    }

    private void refreshBtView() {
        detectView.refreshBtView(btResultList, btMac, btCheckResult);
    }

    @Override
    public void removeBroadcast() {
        AndroidBroadcast.getInstance().removeBroadcast();
    }

    @Override
    public void wifiScanFinish() {
        checkExecutorService.schedule(checkWifiRunnable, 0, TimeUnit.SECONDS);
    }

    private void wifiMouleDetectResultCheck() {
        if (wifiCheckResult == WifiCheckResult.RESULT_PASS) {
            L.d("wifi module is ok......");
            return;
        }
        L.e("warning......wifi module is check fail !!!");
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentMouleAddr = WifiApi.getInstance().getMacAddress();
        if (currentMouleAddr == null) {
            L.d("wifi module not insert");
            return;
        }
        WifiApi.getInstance().startScan();
    }

    private void wifiCompareRssiIsNormal() {
        if (ssidConfigList.isEmpty()) {
            L.e("ssidConfigList is null !!!");
            wifiCheckResult = WifiCheckResult.RESULT_NOT_CONFIG;
            return;
        }

        List<ScanResult> results = WifiApi.getInstance().getScanResults();
        if (results == null || results.isEmpty()) {
            L.d("results is null");
            return;
        }
        for (ScanResult subScanResult : results) {
            L.d("[" + subScanResult.SSID + "]");
        }

        wifiCheckResult = WifiCheckResult.IDLE;
        boolean isAllConfigSsidFind = true;
        boolean isAllConfigLevelNormal = true;
        if (wifiFindResults != null) {
            wifiFindResults.clear();
        }

        for (int i = 0; i < ssidConfigList.size(); i++) {
            String  ssid = ssidConfigList.get(i);
            boolean isFind = false;
            for (ScanResult subScanResult : results) {
                if (subScanResult.SSID.equals(ssid)) {
                    wifiFindResults.put(subScanResult.SSID, subScanResult.level);
                    for (String key : wifiFindResults.keySet()) {
                        L.d("wifiFindResults[" + key + "," + wifiFindResults.get(key) + "]");
                    }
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                int currentPercent = Calculate.getPercentNum(wifiFindResults.get(ssid), standardVauleConfigs.get(ssid));
                if (currentPercent > threshold) {
                    wifiCheckResult = WifiCheckResult.RESULT_RSSI_LEVEL_NG;
                    isAllConfigLevelNormal = false;
                }
            } else {
                wifiCheckResult = WifiCheckResult.RESULT_NOT_FIND;
                isAllConfigSsidFind = false;
            }
        }
        if (!isAllConfigSsidFind && !isAllConfigLevelNormal) {
            wifiCheckResult = WifiCheckResult.RESULT_NOT_FIND_RSSI_LEVEL_NG;
        }
        if (isAllConfigSsidFind && isAllConfigLevelNormal) {
            wifiCheckResult = WifiCheckResult.RESULT_PASS;
        }
    }

    private void wifiCompareMacIsNormal() {
        wifiAddr = WifiApi.getInstance().getMacAddress();
        L.d("wifiAddr=" + wifiAddr);
        boolean isWifiMacMathch = false;
        long wifiAddrCovert = 0L;
        try {
            if (wifiAddr != null) {
                wifiAddrCovert = Long.parseLong(wifiAddr.replace(":", ""), 16);
            }
        } catch (Exception e) {
            L.e("mac format illegal !!!" + e.getMessage() );
        }

        if (wifiAddrCovert >= wifiStartAddr && wifiAddrCovert <= wifiEndAddr) {
            isWifiMacMathch = true;
        }
        L.d("isWifiMacMathch..." + isWifiMacMathch);
        if (!isWifiMacMathch) {
            wifiCheckResult = WifiCheckResult.RESULT_MAC_ADDR_NG;
        }

        if (wifiResultData != null) {
            wifiResultData.clear();
        }
        if (wifiFindResults == null) {
            return;
        }
        for (String key : wifiFindResults.keySet()) {
            WifiConfigSubItem subItem = new WifiConfigSubItem();
            subItem.setConfigSsidName(key);
            subItem.setConfigRssiStandradValue(String.valueOf(wifiFindResults.get(key)));
            wifiResultData.add(subItem);
        }
    }

    @Override
    public void btDiscoveryFound(BluetoothDevice device) {
        this.device = device;
        checkExecutorService.schedule(checkBtRunnable, 0, TimeUnit.SECONDS);
    }

    private void btCompareDeviceIsNormal(BluetoothDevice device) {
        if (btFindConfigNameList.isEmpty()) {
            btCheckResult = BtCheckResult.RESULT_NOT_CONFIG;
            L.d("bt not config");
            return;
        }

        if (device == null) {
            return;
        }
        String deviceName = device.getName();
        L.d("deviceName=" + deviceName);
        if (deviceName == null) {
            return;
        }
        btDeviceNameList.add(deviceName);

        if (btResultList != null) {
            btResultList.clear();
        }
        btCheckResult = BtCheckResult.IDLE;

        boolean isAllconfigNameFind = true;
        for (String btConfigName : btFindConfigNameList) {
            L.d("btConfigName:" + btConfigName);
            boolean isFind = false;
            for (String devName : btDeviceNameList) {
                if (btConfigName.equals(devName)) {
                    btResultList.add(devName);
                    isFind = true;
                    break;
                }
            }
            if (!isFind) {
                btCheckResult = BtCheckResult.RESULT_NOT_FOUND;
                isAllconfigNameFind = false;
            }
        }
        if (isAllconfigNameFind) {
            btCheckResult = BtCheckResult.RESULT_PASS;
        }
    }

    private void btCompareMacIsNormal() {
        btMac = BtApi.getInstance().getMacAddress();
        L.d("btMac=" + btMac);
        boolean isBtMacMathch = false;
        long btAddrCovert = 0L;
        try {
            if (btMac != null) {
                btAddrCovert = Long.parseLong(btMac.replace(":", ""), 16);
            }
        } catch (Exception e) {
            L.e("mac format illegal !!!" + e.getMessage() );
        }

        if (btAddrCovert >= btStartAddr && btAddrCovert <= btEndAddr) {
            isBtMacMathch = true;
        }
        L.d("isBtMacMathch..." + isBtMacMathch);
        if (!isBtMacMathch) {
            btCheckResult = BtCheckResult.RESULT_MAC_ADDR_NG;
        }
    }

    private void btMouleDetectResultCheck() {
        if (btCheckResult == BtCheckResult.RESULT_PASS) {
            L.d("bt module is ok.....");
            BtApi.getInstance().cancelDiscovery();
            btDeviceNameList.clear();
            return;
        }
        L.e("warning......bt module is check fail !!!");
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentMouleAddr = WifiApi.getInstance().getMacAddress();
        if (currentMouleAddr == null) {
            L.d("bt module not insert");
            return;
        }
        BtApi.getInstance().startDiscovery();
    }

    @Override
    public void btDiscoveryFinish() {
        btMouleDetectResultCheck();
    }

    private class IsNewModuleInsertRunnable implements Runnable {

        @Override
        public void run() {
            L.d("IsNewModuleInsertRunnable:thread id=" + Thread.currentThread().getName());
            try {
                if (!isNewModuleInsert()) {
                    return;
                }
                L.d("<new module insert>");
                WifiApi.getInstance().startScan();
                BtApi.getInstance().startDiscovery();
            } catch (Exception e) {
                e.printStackTrace();
                L.d("exception !!!, " + e.getMessage());
            }
        }
    }

    private class CheckWifiRunnable implements Runnable {

        @Override
        public void run() {
            wifiCompareRssiIsNormal();
            wifiCompareMacIsNormal();
            uiHandler.sendEmptyMessage(MSG_REFRESH_WIFI_VIEW);
            wifiMouleDetectResultCheck();
        }
    }

    private class CheckBtRunnable implements Runnable {

        @Override
        public void run() {
            btCompareDeviceIsNormal(device);
            btCompareMacIsNormal();
            uiHandler.sendEmptyMessage(MSG_REFRESH_BT_VIEW);
            if (btCheckResult == BtCheckResult.RESULT_PASS) {
                btMouleDetectResultCheck();
            }
        }
    }

    private static final int MSG_RESET_DEFAULT = 0x00;
    private static final int MSG_REFRESH_WIFI_VIEW = 0x01;
    private static final int MSG_REFRESH_BT_VIEW = 0x02;
    private class UiHandler extends android.os.Handler {

        private UiHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_RESET_DEFAULT:
                    resetDetectView();
                    break;
                case MSG_REFRESH_BT_VIEW:
                    refreshBtView();
                    break;
                case MSG_REFRESH_WIFI_VIEW:
                    refreshWifiView();
                    break;
                default:
                    break;
            }
        }
    }

}
