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
public class WifiListAdapter extends BaseAdapter {

    private List<WifiConfigSubItem> mList = new ArrayList<WifiConfigSubItem>();
    private Context mContext;
    public static final String TAG = "zws";


    public WifiListAdapter(Context context, List<WifiConfigSubItem> list) {
        // TODO Auto-generated constructor stub
        Log.d(TAG, "WifiListAdapter constructer");
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
        WifiConfigSubItem subItem = mList.get(position);

        //动态创建Layout將TextView/Imageview添加ListView中
        Log.d(TAG, "create itemLayout");
        LinearLayout itemLayout = new LinearLayout(mContext);
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ConfigConst.HEIGHT));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        ViewHolder holder = new ViewHolder();
        holder.leftMargin = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, LayoutParams.MATCH_PARENT);
        holder.leftMargin.setLayoutParams(layoutParams);

        holder.ssidName = creatTextView(subItem.getConfigSsidName());
        LinearLayout.LayoutParams ssidLp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        ssidLp.weight = 1;
        holder.ssidName.setLayoutParams(ssidLp);

        holder.level = creatTextView(subItem.getConfigRssiStandradValue());
        LinearLayout.LayoutParams levelLp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        levelLp.weight = 1;
        holder.level.setLayoutParams(levelLp);

        itemLayout.addView(holder.ssidName);
        itemLayout.addView(holder.level);

        return itemLayout;
    }

    private class ViewHolder{
        private TextView leftMargin;
        private TextView ssidName;
        private TextView level;
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
