package com.tianci.btwifitesttool.model.config.base;

import java.io.Serializable;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/18
 */
public class WifiConfigSubItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *  配置的ssid路由名称
     */
    private String configSsidName;
    /**
     *  配置的信号强度标准值
     */
    private String configRssiStandradValue;

    public WifiConfigSubItem() {}

    public String getConfigSsidName() {
        return configSsidName;
    }

    public void setConfigSsidName(String configSsidName) {
        this.configSsidName = configSsidName;
    }

    public String getConfigRssiStandradValue() {
        return configRssiStandradValue;
    }

    public void setConfigRssiStandradValue(String configRssiStandradValue) {
        this.configRssiStandradValue = configRssiStandradValue;
    }

    @Override
    public String toString() {
        return "WifiConfigSubItem{" +
                "configSsidName='" + configSsidName + '\'' +
                ", configRssiStandradValue='" + configRssiStandradValue + '\'' +
                '}';
    }
}
