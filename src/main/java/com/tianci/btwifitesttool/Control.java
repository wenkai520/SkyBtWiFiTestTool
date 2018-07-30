package com.tianci.btwifitesttool;

import com.tianci.btwifitesttool.api.StartApi;
import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.ui.MainActivity;
import com.tianci.btwifitesttool.ui.SettingActivity;
import com.tianci.btwifitesttool.utils.SPUtils;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class Control {
    private static class SingletonHolder {
        private static final Control INSTANCE = new Control();
    }
    private Control(){}
    public static final Control getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startDefaultActivity() {
        Class cls = MainActivity.class;
        if (!hasConfig()) {
            cls = SettingActivity.class;
        }
        StartApi.getInstance().startActivity(cls);
    }

    private boolean hasConfig() {
        return "true".equals(SPUtils.get(ConfigConst.HAS_CONFIG, "false"));
    }

}
