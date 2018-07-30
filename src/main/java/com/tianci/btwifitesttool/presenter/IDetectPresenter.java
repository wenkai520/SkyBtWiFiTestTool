package com.tianci.btwifitesttool.presenter;

import android.bluetooth.BluetoothDevice;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public interface IDetectPresenter {
    void addBroadcast();

    void removeBroadcast();

    void startDetect();

    void stopDetect();

    void wifiScanFinish();

    void btDiscoveryFound(BluetoothDevice device);

    void btDiscoveryFinish();
}
