package com.tianci.btwifitesttool.model.config;

import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * wifi数据配置类
 *
 * @author pis
 * @date 2018/7/18
 */
public class WifiConfigItemData  extends ConfigItemData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * wifi ssid配置子数据列表
     */
    private List<WifiConfigSubItem> wifiConfigSubItemList;

    /**
     * mac地址范围
     */
    private MacConfigRangeItem macConfigRangeItem;

    /**
     * 信号强度百分比阀值
     */
    private String rssiConfigPercentThreshold;

    public WifiConfigItemData() {}

    public List<WifiConfigSubItem> getWifiConfigSubItemList() {
        return wifiConfigSubItemList;
    }

    public void setWifiConfigSubItemList(List<WifiConfigSubItem> wifiConfigSubItemList) {
        this.wifiConfigSubItemList = wifiConfigSubItemList;
    }

    public MacConfigRangeItem getMacConfigRangeItem() {
        return macConfigRangeItem;
    }

    public void setMacConfigRangeItem(MacConfigRangeItem macConfigRangeItem) {
        this.macConfigRangeItem = macConfigRangeItem;
    }

    public String getRssiConfigPercentThreshold() {
        return rssiConfigPercentThreshold;
    }

    public void setRssiConfigPercentThreshold(String rssiConfigPercentThreshold) {
        this.rssiConfigPercentThreshold = rssiConfigPercentThreshold;
    }

    @Override
    public String toString() {
        return "WifiConfigItemData{" +
                "wifiConfigSubItemList=" + wifiConfigSubItemList +
                ", macConfigRangeItem=" + macConfigRangeItem +
                ", rssiConfigPercentThreshold='" + rssiConfigPercentThreshold + '\'' +
                '}';
    }
}
