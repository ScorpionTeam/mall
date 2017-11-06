package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public class OrderExt extends Order {

    public OrderExt (){

    }
    public OrderExt(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * IP地址
     */
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "OrderExt{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
