package com.tianci.btwifitesttool.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;

import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class DetectLayout extends LinearLayout {

    private Context mContex;
    private WifiDetectView wifiDetectView;
    private BtDetectView btDetectView;
    public DetectLayout(Context context) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        setBackgroundResource(R.drawable.ui_sdk_main_page_bg_theme_2);
        setOrientation(VERTICAL);

        mContex = context;
        initView();
    }

    private void initView() {
        wifiDetectView = new WifiDetectView(mContex);
        addView(wifiDetectView);

        btDetectView = new BtDetectView(mContex);
        addView(btDetectView);

        TextView emptyTextView = new TextView(mContex);
        emptyTextView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConfigConst.BOTTOME_LEAVE_HEIGHT));
        addView(emptyTextView);
    }

    public void updateWifiView(BaseAdapter list, String mac, String result) {
        wifiDetectView.updateViewContent(list, mac, result);
    }

    public void updateBtView(BaseAdapter list, String mac, String result) {
        btDetectView.updateViewContent(list, mac, result);
    }
}
