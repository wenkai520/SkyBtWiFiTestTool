package com.tianci.btwifitesttool.ui;

import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.common.ConfigConst.ViewFlag;
import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.model.config.BtConfigItemData;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.presenter.ISettingPresenter;
import com.tianci.btwifitesttool.presenter.impl.SettingPresenterImpl;
import com.tianci.btwifitesttool.ui.base.BaseActivity;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.view.BtConfigView;
import com.tianci.btwifitesttool.view.ISettingView;
import com.tianci.btwifitesttool.view.MainConfigView;
import com.tianci.btwifitesttool.view.MainConfigView.IOnConfigBtnClick;
import com.tianci.btwifitesttool.view.WifiConfigView;
import com.tianci.btwifitesttool.view.base.BaseLayout;
import com.tianci.btwifitesttool.view.base.BaseSettingLayout.IOnSaveBtnClick;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * SettingActivity class
 *
 * @author pis
 * @date 2018/7/12
 */
public class SettingActivity extends BaseActivity implements ISettingView, IOnConfigBtnClick, IOnSaveBtnClick {

    private MainConfigView mainConfigView;
    private WifiConfigView wifiConfigView;
    private BtConfigView btConfigView;
    private BaseLayout currentContentView;
    private ISettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        L.d("SettingActivity onCreate");
        mainConfigView = new MainConfigView(this);
        mainConfigView.setOnConfigBtnClick(this);
        setContentViewInner(mainConfigView);

        settingPresenter = new SettingPresenterImpl(this);
    }

    private void setContentViewInner(BaseLayout view) {
        currentContentView = view;
        setContentView(currentContentView);
    }

    @Override
    public void initView(ViewPage page, ConfigItemData data) {
        switch (page){
            case BT_PAGE:
                btConfigView = new BtConfigView(this);
                setContentViewInner(btConfigView);
                btConfigView.setOnSaveBtnClick(this);
                btConfigView.updateViewData((BtConfigItemData )data);
                break;
            case WIFI_PAGE:
                wifiConfigView = new WifiConfigView(this);
                setContentViewInner(wifiConfigView);
                wifiConfigView.setOnSaveBtnClick(this);
                wifiConfigView.updateViewData((WifiConfigItemData) data);
                break;
            default:
                break;
        }
    }

    @Override
    public ConfigItemData getConfigData(ViewPage page) {
        switch (page) {
            case BT_PAGE:
                return btConfigView.getSaveViewData();
            case WIFI_PAGE:
                return wifiConfigView.getSaveViewData();
            default:
                break;
        }
        return null;
    }

    @Override
    public void backToMainView() {
        Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        setContentViewInner(mainConfigView);
    }

    @Override
    public void onConfigBtnClick(ViewFlag flag) {
        L.d("onConfigBtnClick: " + flag);
        settingPresenter.jumpNext(flag);
        if (flag == ViewFlag.VIEW_START_DETECT) {
            finish();
        }
    }

    @Override
    public void onOnSaveBtnClick(ViewPage page) {
        settingPresenter.saveConfig(page);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           if (currentContentView instanceof WifiConfigView
                   || currentContentView instanceof BtConfigView)
           {
               setContentViewInner(mainConfigView);
               return true;
           }
        }
        return super.onKeyDown(keyCode, event);
    }
}
