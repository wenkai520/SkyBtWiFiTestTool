package com.tianci.btwifitesttool.view.base;

import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.utils.ScreenUtils;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/13
 */
public class BaseBtnTextView extends TextView implements View.OnFocusChangeListener, View.OnKeyListener{

    private IOnBtnclickListener listener;
    public interface IOnBtnclickListener {
        /**
         * 点击事件
         * @param v 响应点击事件的view
         */
        void onBtnClick(View v);
    }

    public void setBtnClickListener(IOnBtnclickListener l) {
        listener = l;
    }

    public BaseBtnTextView(Context context) {
        super(context);

        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.btn_bg_unfocus);
        setTextSize(ScreenUtils.Dpi(25));
        setTextColor(getResources().getColor(R.color.text_unfocus));
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(this);
        setOnKeyListener(this);
        setId(View.generateViewId());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        v.setBackgroundResource(hasFocus ? R.drawable.sky_focus_bg : R.drawable.btn_bg_unfocus);
        ((TextView) v).setTextColor(getResources().getColor(hasFocus ? R.color.text_focus : R.color.text_unfocus));
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP)
        {
            switch (keyCode)
            {
                case KeyEvent.KEYCODE_ENTER:
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    if (listener != null) {
                        listener.onBtnClick(v);
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }

}
