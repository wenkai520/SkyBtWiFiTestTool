package com.tianci.btwifitesttool.view;

import android.content.Context;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.view.base.BaseDetectLayout;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class WifiDetectView extends BaseDetectLayout {
    public WifiDetectView(Context context) {
        super(context, R.string.wifi_name_list, R.string.wifi_rssi_percent, 4 * ConfigConst.HEIGHT);
    }
}
