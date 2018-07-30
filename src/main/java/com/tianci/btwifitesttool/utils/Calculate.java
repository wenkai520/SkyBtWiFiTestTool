package com.tianci.btwifitesttool.utils;

import java.text.NumberFormat;
/**
 * Demo class
 *
 * @author pis
 * @date 2018/7/24
 */
public class Calculate {
    public static int getPercentNum(int diliverNum, int queryMailNum) {

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后几位
        numberFormat.setMaximumFractionDigits(0);
        String result = numberFormat.format((float)diliverNum / (float)queryMailNum * 100);
        return Integer.valueOf(result);
    }
}
