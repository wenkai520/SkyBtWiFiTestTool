package com.tianci.btwifitesttool.api;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.tianci.btwifitesttool.BtWifiTestApplicant;
import com.tianci.btwifitesttool.utils.L;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class WifiApi {

    private WifiManager wifiManager;
    private static class SingletonHolder {
        private static final WifiApi INSTANCE = new WifiApi();
    }
    private WifiApi(){
        wifiManager = (WifiManager ) BtWifiTestApplicant.appContext.getSystemService(Context.WIFI_SERVICE);
    }
    public static final WifiApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startScan() {
        L.d("wait to enable wifi");
        while (true) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            } else {
                break;
            }
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        L.d("wifi enable ok, startScan");
        wifiManager.startScan();
    }

    public List<ScanResult> getScanResults() {
        return wifiManager.getScanResults();
    }

    public String getMacAddress() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return macSerial;
    }
}
