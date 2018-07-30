package com.tianci.btwifitesttool.model.help;

import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.SPUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/18
 */
public class WifiConfigDataHelper {

    private WifiConfigItemData wifiConfigItemData;
    private static class SingletonHolder {
        private static final WifiConfigDataHelper INSTANCE = new WifiConfigDataHelper();
    }

    private WifiConfigDataHelper(){
        wifiConfigItemData = new WifiConfigItemData();
    }

    public static final WifiConfigDataHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ConfigItemData getData() {
        List<WifiConfigSubItem> wifiConfigSubItemList = new ArrayList<WifiConfigSubItem>();
        for (int i = 0; i < ConfigConst.WIFI_TOTOL; i++) {
            WifiConfigSubItem wifiConfigSubItem = new WifiConfigSubItem();
            String name = (String) SPUtils.get(ConfigConst.WIFI_SSID_NAME + "_" + String.valueOf(i + 1), "");
            String standard = (String) SPUtils.get(ConfigConst.WIFI_RSSI_STANDARD + "_" + String.valueOf(i + 1), "-60");
            wifiConfigSubItem.setConfigSsidName(name);
            wifiConfigSubItem.setConfigRssiStandradValue(standard);
            L.d("wifiConfigSubItem[" + i + "]" + wifiConfigSubItem.toString());
            wifiConfigSubItemList.add(wifiConfigSubItem);
        }

        MacConfigRangeItem macConfigRangeItem = new MacConfigRangeItem();
        String start = (String) SPUtils.get(ConfigConst.WIFI_MAC_START_ADDR, ConfigConst.MAC_SRTART_ADDR_DEFAULT);
        String end = (String) SPUtils.get(ConfigConst.WIFI_MAC_END_ADDR, ConfigConst.MAC_END_ADDR_DEFAULT);
        macConfigRangeItem.setStartAddress(start);
        macConfigRangeItem.setEndAddress(end);

        String threshold = (String) SPUtils.get(ConfigConst.WIFI_RSSI_THRESHOLD, "100");

        L.d(wifiConfigSubItemList.toString() + macConfigRangeItem.toString()
                + " threshold:" + threshold);

        wifiConfigItemData.setWifiConfigSubItemList(wifiConfigSubItemList);
        wifiConfigItemData.setMacConfigRangeItem(macConfigRangeItem);
        wifiConfigItemData.setRssiConfigPercentThreshold(threshold);

        return wifiConfigItemData;
    }

    public void saveData(ConfigItemData configData) {
        WifiConfigItemData data = (WifiConfigItemData )configData;
        List<WifiConfigSubItem> wifiConfigSubItemList = data.getWifiConfigSubItemList();
        for (int i = 0; i < ConfigConst.WIFI_TOTOL; i++) {
            SPUtils.put(ConfigConst.WIFI_SSID_NAME + "_" + String.valueOf(i + 1), wifiConfigSubItemList.get(i).getConfigSsidName());
            SPUtils.put(ConfigConst.WIFI_RSSI_STANDARD + "_" + String.valueOf(i + 1), wifiConfigSubItemList.get(i).getConfigRssiStandradValue());
        }
        MacConfigRangeItem macConfigRangeItem = data.getMacConfigRangeItem();
        SPUtils.put(ConfigConst.WIFI_MAC_START_ADDR, macConfigRangeItem.getStartAddress());
        SPUtils.put(ConfigConst.WIFI_MAC_END_ADDR, macConfigRangeItem.getEndAddress());

        SPUtils.put(ConfigConst.WIFI_RSSI_THRESHOLD, data.getRssiConfigPercentThreshold());
    }

}
