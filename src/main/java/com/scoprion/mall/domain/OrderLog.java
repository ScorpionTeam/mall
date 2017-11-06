package com.scoprion.mall.domain;

import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
public class OrderLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号码
     */
    private String orderNo;

    /**
     * 动作
     */
    private String action;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 创建时间
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "OrderLog{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", action='" + action + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
