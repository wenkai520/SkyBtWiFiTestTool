package com.tianci.btwifitesttool.view.base;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.ScreenUtils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/17
 */
public class BaseEdtItem extends LinearLayout {

    private TextView deviceTextView;
    private EditText deviceNameEdt;
    private EditText deviceRssiEdt;
    public BaseEdtItem(Context context, int deviceName) {
        super(context);

        setLayoutParams(new LayoutParams(ScreenUtils.Div(1720), ScreenUtils.Div(113)));
        setOrientation(HORIZONTAL);

        deviceTextView = new TextView(context);
        deviceTextView.setSingleLine();
        deviceTextView.setText(deviceName);
        deviceTextView.setTextSize(ScreenUtils.Dpi(25));
        deviceTextView.setTextColor(getResources().getColor(R.color.text_focus));
        deviceTextView.setLayoutParams(new LayoutParams(ScreenUtils.Div(150), ScreenUtils.Div(100)));
        addView(deviceTextView);

        deviceNameEdt = new BaseEditText(context);
        deviceNameEdt.setLayoutParams(new LayoutParams(ScreenUtils.Div(718 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        addView(deviceNameEdt);

        deviceRssiEdt = new BaseEditText(context);
        deviceRssiEdt.setLayoutParams(new LayoutParams(ScreenUtils.Div(718 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        addView(deviceRssiEdt);
    }

    public void setRightPartGone() {
        deviceTextView.setLayoutParams(new LayoutParams(ScreenUtils.Div(300), ScreenUtils.Div(100)));
        deviceNameEdt.setLayoutParams(new LayoutParams(ScreenUtils.Div(700  + 31 * 2), ScreenUtils.Div(100  + 31 * 2)));
        deviceRssiEdt.setVisibility(View.GONE);
    }

    public void updateEdtItemData(Object subItemdata) {
        if (subItemdata instanceof WifiConfigSubItem) {
            deviceNameEdt.setText(((WifiConfigSubItem) subItemdata).getConfigSsidName());
            deviceRssiEdt.setText(((WifiConfigSubItem) subItemdata).getConfigRssiStandradValue());
            return;
        }

        if (subItemdata instanceof String) {
            deviceNameEdt.setText(subItemdata.toString());
        }
    }

    public Object getEdtItmData(Object defaultObject) {
        if (defaultObject instanceof WifiConfigSubItem) {
            WifiConfigSubItem data = new WifiConfigSubItem();
            data.setConfigSsidName(deviceNameEdt.getText().toString());
            data.setConfigRssiStandradValue(deviceRssiEdt.getText().toString());
            return data;
        }

        if (defaultObject instanceof String) {
            return deviceNameEdt.getText().toString();
        }

        return null;
    }
}
