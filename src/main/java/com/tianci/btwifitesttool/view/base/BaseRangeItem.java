package com.tianci.btwifitesttool.view.base;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;
import com.tianci.btwifitesttool.utils.ScreenUtils;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/17
 */
public class BaseRangeItem extends LinearLayout {

    private TextView macRangeTextview;
    private TextView lineTextview;
    private EditText rangeStartEdt;
    private EditText rangeEndEdt;
    public BaseRangeItem(Context context) {
        super(context);

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.Div(113)));
        setOrientation(HORIZONTAL);

        macRangeTextview = new TextView(context);
        macRangeTextview.setTextSize(ScreenUtils.Dpi(25));
        macRangeTextview.setTextColor(getResources().getColor(R.color.text_focus));
        macRangeTextview.setText(R.string.mac_range);
        macRangeTextview.setLayoutParams(new LayoutParams(ScreenUtils.Div(150), ScreenUtils.Div(100)));
        addView(macRangeTextview);

        rangeStartEdt = new BaseEditText(context);
        rangeStartEdt.setLayoutParams(new LayoutParams(ScreenUtils.Div(700 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        addView(rangeStartEdt);

        lineTextview = new TextView(context);
        lineTextview.setTextSize(ScreenUtils.Dpi(25));
        lineTextview.setTextColor(getResources().getColor(R.color.text_focus));
        lineTextview.setText(R.string.line);
        lineTextview.setLayoutParams(new LayoutParams(ScreenUtils.Div(30), ScreenUtils.Div(100)));
        lineTextview.setGravity(Gravity.CENTER);
        addView(lineTextview);

        rangeEndEdt = new BaseEditText(context);
        rangeEndEdt.setLayoutParams(new LayoutParams(ScreenUtils.Div(700 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        addView(rangeEndEdt);
    }

    public void updatRangeData(MacConfigRangeItem data) {
        rangeStartEdt.setText(data.getStartAddress());
        rangeEndEdt.setText(data.getEndAddress());
    }

    public MacConfigRangeItem getRanageData() {
        MacConfigRangeItem data = new MacConfigRangeItem();
        data.setStartAddress(rangeStartEdt.getText().toString());
        data.setEndAddress(rangeEndEdt.getText().toString());
        return data;
    }

}
