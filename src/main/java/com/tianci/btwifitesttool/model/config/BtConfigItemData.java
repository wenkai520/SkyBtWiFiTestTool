package com.tianci.btwifitesttool.model.config;

import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 蓝牙数据配置类
 *
 * @author pis
 * @date 2018/7/18
 */
public class BtConfigItemData  extends ConfigItemData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**

     *  蓝牙配置名称列表数据
     */
    private List<String> btNameConfigList = new ArrayList<String>(ConfigConst.BT_TOTOL);
    /**
     *  mac地址范围
     */
    private MacConfigRangeItem macConfigRangeItem;

    public BtConfigItemData() {}

    public List<String> getBtNameConfigList() {
        return btNameConfigList;
    }

    public void setBtNameConfigList(List<String> btNameConfigList) {
        this.btNameConfigList = btNameConfigList;
    }

    public MacConfigRangeItem getMacConfigRangeItem() {
        return macConfigRangeItem;
    }

    public void setMacConfigRangeItem(MacConfigRangeItem macConfigRangeItem) {
        this.macConfigRangeItem = macConfigRangeItem;
    }

    @Override
    public String toString() {
        return "BtConfigItemData{" +
                "btNameConfigList=" + btNameConfigList +
                ", macConfigRangeItem=" + macConfigRangeItem +
                '}';
    }


}
