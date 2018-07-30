package com.tianci.btwifitesttool.view;

import com.tianci.btwifitesttool.common.ConfigConst.ViewPage;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;

/**
 * 用户配置相关
 *
 * @author pis
 * @date 2018/7/12
 */
public interface ISettingView {
    /**
     * 初始化界面
     * @param page 当前界面类型
     * @param data 携带的数据
     */
    void initView(ViewPage page, ConfigItemData data);
    /**
     * 获取当前界面的用户数据
     * @param page 当前界面类型
     * @return ConfigItemData 当前界面用户配置的数据
     */
     ConfigItemData getConfigData(ViewPage page);
     /**
      * 返回到设置主界面
      */
     void backToMainView();
}
