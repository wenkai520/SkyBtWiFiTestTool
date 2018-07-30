package com.tianci.btwifitesttool.ui;

import java.util.ArrayList;
import java.util.List;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.api.StartApi;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.common.ConfigConst.WifiCheckResult;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.presenter.IDetectPresenter;
import com.tianci.btwifitesttool.presenter.impl.DetectPresenterImpl;
import com.tianci.btwifitesttool.ui.adapter.BtListAdapter;
import com.tianci.btwifitesttool.ui.adapter.WifiListAdapter;
import com.tianci.btwifitesttool.ui.base.BaseActivity;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.Resource;
import com.tianci.btwifitesttool.view.DetectLayout;
import com.tianci.btwifitesttool.view.IDetectView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class DetectActivity extends BaseActivity implements IDetectView{

    private IDetectPresenter detectPresenter;
    private DetectLayout detectLayout;
    private long firstTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detectLayout = new DetectLayout(this);
        setContentView(detectLayout);

        detectPresenter = new DetectPresenterImpl(this);
        detectPresenter.addBroadcast();
        detectPresenter.startDetect();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detectPresenter.removeBroadcast();
        detectPresenter.stopDetect();
    }

    @Override
    public void refreshWifiView(List<?> list, String mac, WifiCheckResult checkResult) {
        L.d("refreshWifiView");
        List<WifiConfigSubItem> wifiResultList =  (ArrayList<WifiConfigSubItem>) list;
        for (int i = 0; i < list.size(); i++) {
            L.d(wifiResultList.get(i).toString());
        }
        WifiListAdapter listAdapter = new WifiListAdapter(this, wifiResultList);
        L.d("mac:" + mac);
        L.d("checkResult:" + Resource.getString(checkResult.toString()));
        detectLayout.updateWifiView(listAdapter, mac, Resource.getString(checkResult.toString()));
    }

    @Override
    public void refreshBtView(List<?> list, String mac, ConfigConst.BtCheckResult checkResult) {
        L.d("refreshBtView");
        List<String> btResultList =  (ArrayList<String>) list;
        for (int i = 0; i < list.size(); i++) {
            L.d(btResultList.get(i));
        }
        BtListAdapter listAdapter = new BtListAdapter(this, btResultList);
        L.d("mac:" + mac);
        L.d("checkResult:" + Resource.getString(checkResult.toString()));
        detectLayout.updateBtView(listAdapter, mac, Resource.getString(checkResult.toString()));
    }

    /**
     *程序优化，点击两次退出程序
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 800L) {
                Toast.makeText(this, R.string.back_to_setting_view, Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                StartApi.getInstance().startActivity(SettingActivity.class);
                finish();
            }
        }
        return true;
    }
}
