package com.tianci.btwifitesttool.common;

import com.tianci.btwifitesttool.utils.ScreenUtils;

/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/12
 */
public class ConfigConst {

    /**
     * 存储配置项的具体文件名
     */
    public static final String FILE_NAME = "config_data";
    /**
     * 是否引导配置过
     */
    public static final String HAS_CONFIG = "has_config";

    /****************************************************************
     ********************        WIFI相关       **********************
     *****************************************************************/

     public static final int WIFI_TOTOL = 4;
    /**
     * wifi ap 名称
     */
    public static final String WIFI_SSID_NAME = "wifi_ssid_name";
    /**
     * wifi ap 信号强度标准值
     */
    public static final String WIFI_RSSI_STANDARD = "wifi_rssi_standard";
    /**
     * wifi 模块起始地址
     */
    public static final String WIFI_MAC_START_ADDR = "wifi_mac_start_address";
    /**
     * wifi 模块终止地址
     */
    public static final String WIFI_MAC_END_ADDR = "wifi_mac_end_address";

    /**
     * wifi ap 信号强度百分比阀值
     */
    public static final String WIFI_RSSI_THRESHOLD = "wifi_rssi_threshold";


    /****************************************************************
     ********************        蓝牙相关       **********************
     *****************************************************************/

    public static final int BT_TOTOL = 2;
    /**
     * bluetooth 名称
     */
    public static final String BT_DEVICE_NAME = "bt_device_name";
    /**
     * bluetooth 模块起始地址
     */
    public static final String BT_MAC_START_ADDR = "bt_mac_start_address";
    /**
     * bluetooth 模块终止地址
     */
    public static final String BT_MAC_END_ADDR = "bt_mac_end_address";

    /**
     * mac 默认开始地址
     */
    public static final String MAC_SRTART_ADDR_DEFAULT = "00:00:00:00:00:00";
    /**
     * mac 默认终止地址
     */
    public static final String MAC_END_ADDR_DEFAULT = "FF:FF:FF:FF:FF:FF";

    public static final String MAC_ADDR_DEFAULT = "xx:xx:xx:xx:xx:xx";

    public static final int HEIGHT = ScreenUtils.Div(83);

    public static final int BOTTOME_LEAVE_HEIGHT = ScreenUtils.Div(80);

    /**
     * 配置界面按钮标识
     */
    public enum ViewFlag {
        /**
         * 蓝牙按钮
         */
        VIEW_BT,
        /**
         * Wifi按钮
         */
        VIEW_WIFI,
        /**
         * 开始测试按钮
         */
        VIEW_START_DETECT
    }

    /**
     * 配置页面
     */
    public enum ViewPage {
        /**
         * 蓝牙参数配置页
         */
        BT_PAGE,
        /**
         * Wifi参数配置页
         */
        WIFI_PAGE
    }

    /**
     * Wifi检测结果
     */
    public enum WifiCheckResult {
        /**
         * 正常
         */
        RESULT_PASS,
        /**
         * 未配置测试参数
         */
        RESULT_NOT_CONFIG,
        /**
         * 部分ap未发现
         */
        RESULT_NOT_FIND,
        /**
         * 信号强度不合格
         */
        RESULT_RSSI_LEVEL_NG,
        /**
         * 部分ap未发现&&信号强度不合格
         */
        RESULT_NOT_FIND_RSSI_LEVEL_NG,
        /**
         * Mac地址越界
         */
        RESULT_MAC_ADDR_NG,
        /**
         * 空闲
         */
        IDLE
    }

    /**
     * Bt检测结果
     */
    public enum BtCheckResult {
        /**
         * 正常
         */
        RESULT_PASS,
        /**
         * 未配置测试参数
         */
        RESULT_NOT_CONFIG,
        /**
         * Mac地址越界
         */
        RESULT_MAC_ADDR_NG,
        /**
         * 部分蓝牙设备未搜索到
         */
        RESULT_NOT_FOUND,
        /**
         * 空闲
         */
        IDLE
    }
}
