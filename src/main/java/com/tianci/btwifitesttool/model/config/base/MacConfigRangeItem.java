package com.tianci.btwifitesttool.model.config.base;

import java.io.Serializable;

/**
 *  mac地址范围
 *
 * @author pis
 * @date 2018/7/18
 */
public class MacConfigRangeItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *  起始mac地址
     */
    private String startAddress;
    /**
     *  终止mac地址
     */
    private String endAddress;

    public MacConfigRangeItem() {}

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    @Override
    public String toString() {
        return "MacConfigRangeItem{" +
                "startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }
}
