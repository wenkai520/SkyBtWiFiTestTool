package com.tianci.btwifitesttool.view.base;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.utils.ScreenUtils;

import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public class BaseDetectLayout extends LinearLayout{

    private Context mContext;
    private ListView mListView;
    private TextView macResultTextView;
    private TextView checkResultTextView;
    public BaseDetectLayout(Context context, int leftText, int rightText, int listviewHigh) {
        super(context);
        mContext = context;
        setOrientation(VERTICAL);

        initTitle(leftText, rightText);
        initListView(listviewHigh);
        initMacResultView();
        initCheckResult();
    }

    private void initCheckResult() {
        LinearLayout checkLayout = new LinearLayout(mContext);
        checkLayout.setOrientation(HORIZONTAL);
        LayoutParams checkLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, ConfigConst.HEIGHT);
        addView(checkLayout, checkLayoutLp);

        TextView leftTextView = creatTextView(R.string.check_result);
        LayoutParams leftTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        leftTextViewLp.weight = 1;
        checkLayout.addView(leftTextView, leftTextViewLp);

        checkResultTextView = creatTextView(R.string.empty);
        checkResultTextView.setTextSize(ScreenUtils.Dpi(30));
        LayoutParams rightTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        rightTextViewLp.weight = 1;
        checkLayout.addView(checkResultTextView, rightTextViewLp);
    }

    private void initMacResultView() {
        LinearLayout macLayout = new LinearLayout(mContext);
        macLayout.setOrientation(HORIZONTAL);
        LayoutParams macLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, ConfigConst.HEIGHT);
        addView(macLayout, macLayoutLp);

        TextView leftTextView = creatTextView(R.string.mac);
        LayoutParams leftTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        leftTextViewLp.weight = 1;
        macLayout.addView(leftTextView, leftTextViewLp);

        macResultTextView = creatTextView(R.string.empty);
        LayoutParams rightTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        rightTextViewLp.weight = 1;
        macLayout.addView(macResultTextView, rightTextViewLp);
    }

    private void initListView(int high) {
        mListView = new ListView(mContext);
        mListView.setFocusable(false);
        LayoutParams listViewLp = new LayoutParams(LayoutParams.MATCH_PARENT, high);
        addView(mListView, listViewLp);
    }

    private void initTitle(int leftText, int rightText) {
        LinearLayout titleLayout = new LinearLayout(mContext);
        titleLayout.setOrientation(HORIZONTAL);
        LayoutParams titleLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, ConfigConst.HEIGHT);
        addView(titleLayout, titleLayoutLp);

        TextView leftTextView = creatTextView(leftText);
        leftTextView.setTextSize(ScreenUtils.Dpi(35));
        leftTextView.getPaint().setFakeBoldText(true);
        LayoutParams leftTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        leftTextViewLp.weight = 1;
        titleLayout.addView(leftTextView, leftTextViewLp);

        TextView rightTextView = creatTextView(rightText);
        rightTextView.setTextSize(ScreenUtils.Dpi(35));
        rightTextView.getPaint().setFakeBoldText(true);
        LayoutParams rightTextViewLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        rightTextViewLp.weight = 1;
        titleLayout.addView(rightTextView, rightTextViewLp);
    }

    private TextView creatTextView(int text) {
        TextView textView = new TextView(mContext);
        textView.setSingleLine();
        textView.setText(text);
        textView.setTextSize(ScreenUtils.Dpi(25));
        textView.setTextColor(getResources().getColor(R.color.text_focus));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public void updateViewContent(BaseAdapter adapter, String mac, String result) {

        mListView.setAdapter(adapter);
        macResultTextView.setText(mac);
        if (result.equalsIgnoreCase(getResources().getString(R.string.pass))) {
            checkResultTextView.setTextColor(Color.GREEN);
        } else {
            checkResultTextView.setTextColor(Color.RED);
        }
        checkResultTextView.setText(result);
    }

}
