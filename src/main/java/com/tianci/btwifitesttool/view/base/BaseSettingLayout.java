package com.tianci.btwifitesttool.view.base;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.utils.ScreenUtils;
import com.tianci.btwifitesttool.view.base.BaseBtnTextView.IOnBtnclickListener;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class BaseSettingLayout extends BaseLayout implements IOnBtnclickListener {

    public interface IOnSaveBtnClick
    {
        /**
         * 保存按钮点击事件
         * @param page 当前是从哪个页面来的
         */
        void onOnSaveBtnClick(ViewPage page);
    }

    private IOnSaveBtnClick listener;
    public void setOnSaveBtnClick(IOnSaveBtnClick l)
    {
        listener = l;
    }

    @Override
    public void onBtnClick(View v) {
        if (listener == null) {
            return;
        }
        listener.onOnSaveBtnClick(currentViewPage);
    }

    private Context mContext;
    private ViewPage currentViewPage;
    protected BaseRangeItem macRangeItem;
    protected BaseEdtItem rssiThresholdItem;
    protected BaseBtnTextView saveBtn;
    public BaseSettingLayout(Context context, int title, ViewPage page) {
        super(context, title);
        mContext = context;
        currentViewPage = page;

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setBackgroundResource(R.drawable.ui_sdk_main_page_bg_theme_2);
    }

    protected void addTopView(int leftText, int rightText) {
        LinearLayout topLayout = new LinearLayout(mContext);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, ScreenUtils.Div(130));
        lp.gravity = Gravity.TOP;
        lp.topMargin = ScreenUtils.Div(80);
        lp.leftMargin = ScreenUtils.Div(550);
        addView(topLayout, lp);

        TextView leftTextView = new TextView(mContext);
        leftTextView.setSingleLine();
        leftTextView.setText(leftText);
        leftTextView.setTextSize(ScreenUtils.Dpi(30));
        leftTextView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

        LayoutParams leftTextViewLp = new LayoutParams(ScreenUtils.Div(500), LayoutParams.MATCH_PARENT);
        topLayout.addView(leftTextView, leftTextViewLp);

        TextView emptyTextView = new TextView(mContext);
        LayoutParams emptyTextViewLp = new LayoutParams(ScreenUtils.Div(200), LayoutParams.MATCH_PARENT);
        topLayout.addView(emptyTextView, emptyTextViewLp);

        TextView rightTextView = new TextView(mContext);
        rightTextView.setSingleLine();
        rightTextView.setText(rightText);
        rightTextView.setTextSize(ScreenUtils.Dpi(30));
        rightTextView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

        LayoutParams rightTextViewLp = new LayoutParams(ScreenUtils.Div(500), LayoutParams.MATCH_PARENT);
        rightTextViewLp.rightMargin = ScreenUtils.Div(100);
        topLayout.addView(rightTextView, rightTextViewLp);
    }

    protected void addBottomView() {
        LinearLayout bottomContentLayout = new LinearLayout(mContext);
        bottomContentLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.Div(400));
        lp.gravity = Gravity.BOTTOM;
        lp.leftMargin = ScreenUtils.Div(100);
        addView(bottomContentLayout, lp);

        macRangeItem = new BaseRangeItem(mContext);
        bottomContentLayout.addView(macRangeItem);

        rssiThresholdItem = new BaseEdtItem(mContext, R.string.percent_threshold);
        rssiThresholdItem.setRightPartGone();
        bottomContentLayout.addView(rssiThresholdItem);

        LinearLayout saveLayout = new LinearLayout(mContext);
        saveLayout.setOrientation(LinearLayout.HORIZONTAL);
        saveLayout.setGravity(Gravity.CENTER);

        LayoutParams saveLayoutlp = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.Div(113));
        saveLayoutlp.topMargin = ScreenUtils.Div(20);
        bottomContentLayout.addView(saveLayout, saveLayoutlp);

        saveBtn = new BaseBtnTextView(mContext);
        LayoutParams saveBtnLp = new LayoutParams(new LayoutParams(ScreenUtils.Div(300 + 31 * 2), ScreenUtils.Div(100 + 31 * 2)));
        saveBtn.setText(R.string.save);
        saveBtn.setBtnClickListener(this);
        saveLayout.addView(saveBtn, saveBtnLp);
    }

    protected void setRssiThresholdItemGone()
    {
        rssiThresholdItem.setVisibility(View.GONE);
    }

}
