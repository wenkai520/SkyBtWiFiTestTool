package com.tianci.btwifitesttool.view.base;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.utils.ScreenUtils;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class BaseLayout extends FrameLayout {

    private Context mContext;
    public BaseLayout(Context context, int title) {
        super(context);
        mContext = context;

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setBackgroundResource(R.drawable.ui_sdk_main_page_bg_theme_2);

        addTitle(title);
    }

    private void addTitle(int title) {
        TextView titleTextView = new TextView(mContext);
        titleTextView.setSingleLine();
        titleTextView.setText(title);
        titleTextView.setTextSize(ScreenUtils.Dpi(40));
        titleTextView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

        LayoutParams titleTextViewLp = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.Div(130));
        titleTextViewLp.gravity = Gravity.TOP;
        addView(titleTextView, titleTextViewLp);
    }

}
