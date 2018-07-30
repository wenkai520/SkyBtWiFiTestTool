package com.tianci.btwifitesttool;

import android.app.Application;

import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.ScreenUtils;

/**
 * BtWifiTestApplicant class
 *
 * @author pis
 * @date 2018/7/12
 */
public class BtWifiTestApplicant extends Application {

    public static BtWifiTestApplicant appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        L.isDebug = true;
        ScreenUtils.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
