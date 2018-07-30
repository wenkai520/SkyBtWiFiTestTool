package com.tianci.btwifitesttool.view.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.utils.ScreenUtils;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/17
 */
public class BaseEditText extends EditText implements View.OnClickListener, View.OnFocusChangeListener{
    public BaseEditText(Context context) {
        super(context);

        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.btn_bg_unfocus);
        setTextSize(ScreenUtils.Dpi(25));
        setTextColor(getResources().getColor(R.color.text_unfocus));
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(this);
        setId(View.generateViewId());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        v.setBackgroundResource(hasFocus ? R.drawable.sky_focus_bg : R.drawable.btn_bg_unfocus);
        ((TextView) v).setTextColor(getResources().getColor(hasFocus ? R.color.text_focus : R.color.text_unfocus));
    }
}
