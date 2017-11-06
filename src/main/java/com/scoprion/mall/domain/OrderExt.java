package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public class OrderExt extends Order {

    public OrderExt() {

    }

    public OrderExt(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 购买数量
     */
    private int count;

    /**
     * 买家留言
     */
    private String message;

    /**
     * 优惠券id
     */
    private Long ticketId;
    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 微信用户标识code
     */
    private String openCode;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    @Override
    public String toString() {
        return "OrderExt{" +
                "goodId=" + goodId +
                ", count=" + count +
                ", message='" + message + '\'' +
                ", ticketId=" + ticketId +
                ", ipAddress='" + ipAddress + '\'' +
                ", openCode='" + openCode + '\'' +
                '}';
    }
}
