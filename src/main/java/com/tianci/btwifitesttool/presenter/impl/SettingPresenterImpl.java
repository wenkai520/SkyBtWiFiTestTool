package com.tianci.btwifitesttool.presenter.impl;

import com.tianci.btwifitesttool.api.StartApi;
import com.tianci.btwifitesttool.common.ConfigConst.ViewFlag;
import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.model.config.BtConfigItemData;
import com.tianci.btwifitesttool.model.config.WifiConfigItemData;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.help.BtConfigDataHelper;
import com.tianci.btwifitesttool.model.help.WifiConfigDataHelper;
import com.tianci.btwifitesttool.presenter.ISettingPresenter;
import com.tianci.btwifitesttool.ui.DetectActivity;
import com.tianci.btwifitesttool.view.ISettingView;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class SettingPresenterImpl implements ISettingPresenter {

    private ISettingView settingView;
    private ConfigItemData btConfigItemData;
    private ConfigItemData wifiConfigItemData;
    public SettingPresenterImpl(ISettingView v)
    {
        settingView = v;
        btConfigItemData = new BtConfigItemData();
        wifiConfigItemData = new WifiConfigItemData();
    }

    @Override
    public void saveConfig(ViewPage page) {
        ConfigItemData data = settingView.getConfigData(page);
        switch (page) {
            case WIFI_PAGE:
                WifiConfigDataHelper.getInstance().saveData(data);
                break;
            case BT_PAGE:
                BtConfigDataHelper.getInstance().saveData(data);
                break;
            default:
                break;
        }
        settingView.backToMainView();
    }

    @Override
    public void jumpNext(ViewFlag flag) {
        switch (flag) {
            case VIEW_BT:
                btConfigItemData =  BtConfigDataHelper.getInstance().getData();
                settingView.initView(ViewPage.BT_PAGE, btConfigItemData);
                break;
            case VIEW_WIFI:
                wifiConfigItemData = WifiConfigDataHelper.getInstance().getData();
                settingView.initView(ViewPage.WIFI_PAGE, wifiConfigItemData);
                break;
            case VIEW_START_DETECT:
                StartApi.getInstance().startActivity(DetectActivity.class);
                break;
            default:
                break;
        }
    }

}
