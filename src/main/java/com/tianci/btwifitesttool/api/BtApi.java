package com.tianci.btwifitesttool.api;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;

import com.tianci.btwifitesttool.utils.L;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class BtApi {
    BluetoothAdapter btAdapter;
    private static class SingletonHolder {
        private static final BtApi INSTANCE = new BtApi();
    }
    private BtApi(){
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    public static final BtApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startDiscovery() {
        L.d("wait to enable bt");
        while (true) {
            if (!btAdapter.isEnabled()) {
                btAdapter.enable();
            } else {
                break;
            }
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        L.d("enable bt ok , startDiscovery");
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
        }
        btAdapter.startDiscovery();
    }

    public void cancelDiscovery() {
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
        }
    }

    public String getMacAddress() {
        if (Build.VERSION.SDK_INT < 23) {
            return btAdapter.getAddress();
        } else {
            return getBluetoothAddressSdk23(btAdapter);
        }
    }

    /**
     * Android API23以上,出于安全性考虑,官方不允许非系统应用获取蓝牙的MAC地址,调用
     * {@link BluetoothAdapter#getAddress()}方法会返回固定的默认值,无法获得正确的蓝牙地址。
     * 可以通过反射绕过这个限制,得到正确的MAC地址。
     * <p/>
     * 注意:此方法仅针对API23以上,低于这个版本的调用{@link BluetoothAdapter#getAddress()}
     * 可以得到正确的蓝牙地址。
     *
     * @param adapter 蓝牙设配器
     * @return 蓝牙的MAC地址
     */
    @TargetApi(23)
    static String getBluetoothAddressSdk23(BluetoothAdapter adapter) {
        if (adapter == null) {
            return null;
        }

        Class<? extends BluetoothAdapter> btAdapterClass = adapter.getClass();
        try {
            Class<?> btClass = Class.forName("android.bluetooth.IBluetooth");
            Field bluetooth = btAdapterClass.getDeclaredField("mService");
            bluetooth.setAccessible(true);
            Method btAddress = btClass.getMethod("getAddress");
            btAddress.setAccessible(true);
            return (String) btAddress.invoke(bluetooth.get(adapter));
        } catch (Exception e) {
            L.d( "Call Bluetooth by reflection failed.");
            return adapter.getAddress();
        }
    }
}

