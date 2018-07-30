package com.tianci.btwifitesttool.view;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.model.config.BtConfigItemData;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.ScreenUtils;
import com.tianci.btwifitesttool.view.base.BaseEdtItem;
import com.tianci.btwifitesttool.view.base.BaseSettingLayout;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class BtConfigView extends BaseSettingLayout {

    private List<BaseEdtItem> btEdtNameList = new ArrayList<BaseEdtItem>();
    private int[] bluetooth = new int[]{R.string.bluetooth1, R.string.bluetooth2};
    private Context mContext;
    public BtConfigView(Context context) {
        super(context, R.string.bt_config_title, ViewPage.BT_PAGE);

        mContext = context;

        addTopView(R.string.bt_name_list, R.string.bt_rssi_standard);
        initView();
        addBottomView();
        setRssiThresholdItemGone();
    }

    private void initView() {
        LinearLayout contentLayout = new LinearLayout(mContext);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams contentLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        contentLayoutLp.topMargin = ScreenUtils.Div(140);
        contentLayoutLp.bottomMargin = ScreenUtils.Div(400);
        contentLayoutLp.leftMargin = ScreenUtils.Div(100);
        addView(contentLayout, contentLayoutLp);

        for (int i = 0; i < bluetooth.length; i++) {
            BaseEdtItem edtItem = new BaseEdtItem(mContext, bluetooth[i]);
            edtItem.setRightPartGone();
            contentLayout.addView(edtItem);
            btEdtNameList.add(edtItem);
        }
    }

    public void updateViewData(BtConfigItemData data) {
        L.d(data.toString());
        for (int i = 0 ; i < btEdtNameList.size() ; i ++) {
            btEdtNameList.get(i).updateEdtItemData(data.getBtNameConfigList().get(i));
        }
        macRangeItem.updatRangeData(data.getMacConfigRangeItem());
    }

    public BtConfigItemData getSaveViewData() {
        BtConfigItemData data = new BtConfigItemData();
        List<String> btSubItems = new ArrayList<String>();
        L.d("btEdtNameList size = " + btEdtNameList.size());
        for (int i = 0 ; i < btEdtNameList.size() ; i++) {
            String subData = (String )btEdtNameList.get(i).getEdtItmData("");
            L.d("subBtConfigitem" + "[" + i + "]" + subData.toString());
            btSubItems.add(subData);
        }
        data.setBtNameConfigList(btSubItems);
        data.setMacConfigRangeItem(macRangeItem.getRanageData());

        return data;
    }

}
