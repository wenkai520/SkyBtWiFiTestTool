package com.tianci.btwifitesttool.utils;

import com.tianci.btwifitesttool.BtWifiTestApplicant;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/21
 */
public class Resource {
    public static String getString(String name) {
        if (name == null) {
            L.e("exception, name is null !");
            return "";
        }
        String str = BtWifiTestApplicant.appContext
                .getResources()
                .getText(
                        BtWifiTestApplicant.appContext.getResources().getIdentifier(name, "string",
                                BtWifiTestApplicant.appContext.getPackageName())).toString();
        return str;
    }
}
