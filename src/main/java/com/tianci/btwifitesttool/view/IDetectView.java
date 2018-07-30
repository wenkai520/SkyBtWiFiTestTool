package com.tianci.btwifitesttool.view;

import java.util.List;

import com.tianci.btwifitesttool.common.ConfigConst.BtCheckResult;
import com.tianci.btwifitesttool.common.ConfigConst.WifiCheckResult;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/20
 */
public interface IDetectView {
    void refreshWifiView(List<?> list, String mac, WifiCheckResult checkResult);
    void refreshBtView(List<?> list, String mac, BtCheckResult checkResult);
}
