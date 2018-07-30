package com.tianci.btwifitesttool.view;

import android.content.Context;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.view.base.BaseDetectLayout;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class BtDetectView extends BaseDetectLayout {
    public BtDetectView(Context context) {
        super(context, R.string.bt_name_list, R.string.empty, 90 * 2);
    }
}
