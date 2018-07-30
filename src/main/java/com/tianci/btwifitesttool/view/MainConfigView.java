package com.tianci.btwifitesttool.view;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst.ViewFlag;
import com.tianci.btwifitesttool.utils.ScreenUtils;
import com.tianci.btwifitesttool.view.base.BaseBtnTextView;
import com.tianci.btwifitesttool.view.base.BaseLayout;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class MainConfigView extends BaseLayout implements BaseBtnTextView.IOnBtnclickListener{
    private Context mContext;
    private BaseBtnTextView btConfigBtn;
    private BaseBtnTextView wifiConfigBtn;
    private BaseBtnTextView startDetectBtn;
    private IOnConfigBtnClick listener;

    public interface IOnConfigBtnClick
    {
        /**
         * 按钮点击事件
         * @param flag 当前点击的是哪种按钮
         */
        void onConfigBtnClick(ViewFlag flag);
    }

    public void setOnConfigBtnClick(IOnConfigBtnClick l)
    {
        listener = l;
    }

    public MainConfigView(Context context) {
        super(context, R.string.main_config_title);
        mContext = context;

        initView();
    }

    private void initView() {
        btConfigBtn = new BaseBtnTextView(mContext);
        LayoutParams btConfigBtnLp = new LayoutParams(new LayoutParams(ScreenUtils.Div(300 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        btConfigBtnLp.topMargin = ScreenUtils.Div(200);
        btConfigBtnLp.gravity = Gravity.CENTER_HORIZONTAL;
        btConfigBtn.setText(R.string.bt_config_title);
        btConfigBtn.setBtnClickListener(this);
        addView(btConfigBtn, btConfigBtnLp);

        wifiConfigBtn = new BaseBtnTextView(mContext);
        LayoutParams wifiConfigBtnLp = new LayoutParams(new LayoutParams(ScreenUtils.Div(300 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        wifiConfigBtnLp.topMargin = ScreenUtils.Div(400);
        wifiConfigBtnLp.gravity = Gravity.CENTER_HORIZONTAL;
        wifiConfigBtn.setText(R.string.wifi_config_title);
        wifiConfigBtn.setBtnClickListener(this);
        addView(wifiConfigBtn, wifiConfigBtnLp);

        startDetectBtn = new BaseBtnTextView(mContext);
        LayoutParams startDetectBtnLp = new LayoutParams(new LayoutParams(ScreenUtils.Div(300 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        startDetectBtnLp.topMargin = ScreenUtils.Div(600);
        startDetectBtnLp.gravity = Gravity.CENTER_HORIZONTAL;
        startDetectBtn.setText(R.string.start_detect);
        startDetectBtn.setBtnClickListener(this);
        addView(startDetectBtn, startDetectBtnLp);
    }

    @Override
    public void onBtnClick(View v) {
        if (listener == null) {
            return;
        }
        if (v.getId() == btConfigBtn.getId()) {
            listener.onConfigBtnClick(ViewFlag.VIEW_BT);
            return;
        }
        if (v.getId() == wifiConfigBtn.getId()) {
            listener.onConfigBtnClick(ViewFlag.VIEW_WIFI);
            return;
        }
        if (v.getId() == startDetectBtn.getId()) {
            listener.onConfigBtnClick(ViewFlag.VIEW_START_DETECT);
            return;
        }
    }
}
