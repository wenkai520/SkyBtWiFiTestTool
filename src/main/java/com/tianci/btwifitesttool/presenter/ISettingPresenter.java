package com.tianci.btwifitesttool.presenter;

import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.common.ConfigConst.ViewFlag;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public interface ISettingPresenter {
    /**
     * 保存配置数据
     * @param page 当前界面
     */
    void saveConfig(ViewPage page);
    /**
     * 调转到下一页面
     * @param flag 当前点击的btn标识
     */
    void jumpNext(ViewFlag flag);
}
