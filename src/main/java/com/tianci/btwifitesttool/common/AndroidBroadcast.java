package com.tianci.btwifitesttool.common;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import com.tianci.btwifitesttool.BtWifiTestApplicant;
import com.tianci.btwifitesttool.presenter.IDetectPresenter;
import com.tianci.btwifitesttool.utils.L;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class AndroidBroadcast {

    private WifiScanFinishBroadcastReceiver wifiReceiver;
    private BtScanFinishBroadcastReceiver btReceiver;
    private static class SingletonHolder {
        private static final AndroidBroadcast INSTANCE = new AndroidBroadcast();
    }
    private AndroidBroadcast(){
    }
    public static final AndroidBroadcast getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addBroadcast(IDetectPresenter presenter) {
        wifiReceiver = new WifiScanFinishBroadcastReceiver(presenter);
        IntentFilter wifiIntentFilter = new IntentFilter();
        wifiIntentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        BtWifiTestApplicant.appContext.registerReceiver(wifiReceiver, wifiIntentFilter);

        btReceiver = new BtScanFinishBroadcastReceiver(presenter);
        IntentFilter btIntentFilter = new IntentFilter();
        btIntentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        btIntentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        BtWifiTestApplicant.appContext.registerReceiver(btReceiver, btIntentFilter);
    }

    public void removeBroadcast() {
        if (wifiReceiver != null) {
            BtWifiTestApplicant.appContext.unregisterReceiver(wifiReceiver);
        }
        if (btReceiver != null) {
            BtWifiTestApplicant.appContext.unregisterReceiver(btReceiver);
        }
    }

    private class WifiScanFinishBroadcastReceiver extends BroadcastReceiver {
        private IDetectPresenter presenter;
        public WifiScanFinishBroadcastReceiver(IDetectPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            L.d("onReceive: " + action);
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                L.d("wifi scan finish, callback");
                presenter.wifiScanFinish();
            }
        }
    }

    private class BtScanFinishBroadcastReceiver extends BroadcastReceiver {
        private IDetectPresenter presenter;
        public BtScanFinishBroadcastReceiver(IDetectPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //L.d("onReceive: " + action);
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                presenter.btDiscoveryFinish();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                L.d("bt device fonud, callback");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                presenter.btDiscoveryFound(device);
            }
        }
    }

}
