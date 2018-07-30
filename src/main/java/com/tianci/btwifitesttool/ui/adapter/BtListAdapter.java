package com.tianci.btwifitesttool.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.ScreenUtils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/23
 */
public class BtListAdapter extends BaseAdapter {

    private List<String> mList = new ArrayList<String>();
    private Context mContext;
    public static final String TAG = "zws";


    public BtListAdapter(Context context, List<String> list) {
        // TODO Auto-generated constructor stub
        Log.d(TAG, "BtListAdapter constructer");
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "list size =" + mList.size());
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        String devName = mList.get(position);

        //动态创建Layout將TextView/Imageview添加ListView中
        Log.d(TAG, "create itemLayout");
        LinearLayout itemLayout = new LinearLayout(mContext);
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ConfigConst.HEIGHT));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        ViewHolder holder = new ViewHolder();
        holder.deviceName = creatTextView(devName);
        LinearLayout.LayoutParams deviceNameLp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        deviceNameLp.weight = 1;
        holder.deviceName.setLayoutParams(deviceNameLp);

        holder.rightMargin = creatTextView("");
        LinearLayout.LayoutParams rightMarginLp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        rightMarginLp.weight = 1;
        holder.rightMargin.setLayoutParams(rightMarginLp);

        itemLayout.addView(holder.deviceName);
        itemLayout.addView(holder.rightMargin);

        return itemLayout;
    }

    private class ViewHolder{
        private TextView deviceName;
        private TextView rightMargin;
    }

    private TextView creatTextView(String text) {
        TextView textView = new TextView(mContext);
        textView.setSingleLine();
        textView.setText(text);
        textView.setTextSize(ScreenUtils.Dpi(25));
        textView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
