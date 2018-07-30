package com.tianci.btwifitesttool.api;

import android.content.Intent;

import com.tianci.btwifitesttool.BtWifiTestApplicant;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class StartApi {
    private static class SingletonHolder {
        private static final StartApi INSTANCE = new StartApi();
    }
    private StartApi(){}
    public static final StartApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startActivity(Class cls) {
        Intent intent = new Intent();
        intent.setClass(BtWifiTestApplicant.appContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BtWifiTestApplicant.appContext.startActivity(intent);
    }

}

