package com.tianci.btwifitesttool.model.help;

import com.tianci.btwifitesttool.common.ConfigConst;
import com.tianci.btwifitesttool.model.config.BtConfigItemData;
import com.tianci.btwifitesttool.model.config.base.ConfigItemData;
import com.tianci.btwifitesttool.model.config.base.MacConfigRangeItem;
import com.tianci.btwifitesttool.model.config.base.WifiConfigSubItem;
import com.tianci.btwifitesttool.utils.L;
import com.tianci.btwifitesttool.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/18
 */
public class BtConfigDataHelper {

    private BtConfigItemData btConfigItemData;

    private static class SingletonHolder {
        private static final BtConfigDataHelper INSTANCE = new BtConfigDataHelper();
    }

    private BtConfigDataHelper(){
        btConfigItemData = new BtConfigItemData();
    }

    public static final BtConfigDataHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ConfigItemData getData() {
        List<String> btNameConfigList = new ArrayList<String>();
        for (int i = 0; i < ConfigConst.BT_TOTOL; i++) {
            String name = (String) SPUtils.get(ConfigConst.BT_DEVICE_NAME + "_" + String.valueOf(i + 1), "");
            L.d("btNameConfig[" + i + "]" + name);
            btNameConfigList.add(name);
        }

        MacConfigRangeItem macConfigRangeItem = new MacConfigRangeItem();
        String start = (String) SPUtils.get(ConfigConst.BT_MAC_START_ADDR, ConfigConst.MAC_SRTART_ADDR_DEFAULT);
        String end = (String) SPUtils.get(ConfigConst.BT_MAC_END_ADDR, ConfigConst.MAC_END_ADDR_DEFAULT);
        macConfigRangeItem.setStartAddress(start);
        macConfigRangeItem.setEndAddress(end);

        btConfigItemData.setBtNameConfigList(btNameConfigList);
        btConfigItemData.setMacConfigRangeItem(macConfigRangeItem);

        return btConfigItemData;
    }

    public void saveData(ConfigItemData configData) {
        BtConfigItemData data = (BtConfigItemData )configData;
        List<String> btNameConfigList = data.getBtNameConfigList();
        for (int i = 0; i < ConfigConst.BT_TOTOL; i++) {
            SPUtils.put(ConfigConst.BT_DEVICE_NAME + "_" + String.valueOf(i + 1), btNameConfigList.get(i));
        }

        MacConfigRangeItem macConfigRangeItem = data.getMacConfigRangeItem();
        SPUtils.put(ConfigConst.BT_MAC_START_ADDR, macConfigRangeItem.getStartAddress());
        SPUtils.put(ConfigConst.BT_MAC_END_ADDR, macConfigRangeItem.getEndAddress());
    }

}
