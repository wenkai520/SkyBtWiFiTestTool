package com.tianci.btwifitesttool.view;

import java.util.ArrayList;
import java.util.List;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.ScreenUtils;
import com.tianci.btwifitesttool.view.base.BaseEdtItem;
import com.tianci.btwifitesttool.view.base.BaseSettingLayout;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class WifiConfigView extends BaseSettingLayout {

    private List<BaseEdtItem> ssidEdtList = new ArrayList<BaseEdtItem>(ConfigConst.WIFI_TOTOL);
    private List<WifiConfigSubItem> wifiSubItems = new ArrayList<WifiConfigSubItem>();
    private int[] rssi = new int[]{R.string.ssid1, R.string.ssid2, R.string.ssid3, R.string.ssid4};
    private Context mContext;
    public WifiConfigView(Context context) {
        super(context, R.string.wifi_config_title, ViewPage.WIFI_PAGE);
        mContext = context;

        addTopView(R.string.wifi_name_list, R.string.wifi_rssi_standard);
        initView();
        addBottomView();
    }

    private void initView() {
        LinearLayout contentLayout = new LinearLayout(mContext);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams contentLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        contentLayoutLp.topMargin = ScreenUtils.Div(140);
        contentLayoutLp.bottomMargin = ScreenUtils.Div(400);
        contentLayoutLp.leftMargin = ScreenUtils.Div(100);
        addView(contentLayout, contentLayoutLp);

        for (int i = 0; i < rssi.length; i++) {
            BaseEdtItem edtItem = new BaseEdtItem(mContext, rssi[i]);
            contentLayout.addView(edtItem);
            L.d("addview " + i);
            ssidEdtList.add(edtItem);
        }
    }

    public void updateViewData(WifiConfigItemData data) {
        L.d(data.toString());
        for (int i = 0 ; i < ssidEdtList.size() ; i ++) {
            ssidEdtList.get(i).updateEdtItemData(data.getWifiConfigSubItemList().get(i));
        }
        macRangeItem.updatRangeData(data.getMacConfigRangeItem());
        rssiThresholdItem.updateEdtItemData(data.getRssiConfigPercentThreshold());
    }

    public WifiConfigItemData getSaveViewData() {
        WifiConfigItemData data = new WifiConfigItemData();
        WifiConfigSubItem defaultSubItemData = new WifiConfigSubItem();
        L.d("ssidEdtList size = " + ssidEdtList.size());
        for (int i = 0 ; i < ssidEdtList.size() ; i++) {
            defaultSubItemData = (WifiConfigSubItem)ssidEdtList.get(i).getEdtItmData(defaultSubItemData);
            L.d("subWifiConfigitem" + "[" + i + "]" + defaultSubItemData.toString());
            wifiSubItems.add(defaultSubItemData);
        }
        data.setWifiConfigSubItemList(wifiSubItems);
        String defaultString = "";
        data.setMacConfigRangeItem(macRangeItem.getRanageData());
        data.setRssiConfigPercentThreshold((String )rssiThresholdItem.getEdtItmData(defaultString));

        return data;
    }
}
