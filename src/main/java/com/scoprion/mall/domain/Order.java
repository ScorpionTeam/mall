package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/9/28.
 */
public class Order {

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品快照id
     */
    private Long goodSnapShotId;

    /**
     * 配送地址id
     */
    private Long deliveryId;

    /**
     * 状态
     * 0 全部
     * 1 待付款
     * 2 待发货
     * 3 待收货
     * 4 已完成
     */
    private String orderStatus;

    /**
     * 订单类型 1pc订单  2手机订单
     */
    private String orderType;

    /**
     * 支付类型
     * 0 支付宝
     * 1 微信
     * 2 信用卡
     * 3 储蓄卡
     */
    private String payType;

    /**
     * 下单时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 支付时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;

    /**
     * 发货时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryDate;

    //商品快照
    @Transient
    private GoodSnapshot goodSnapshot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getGoodSnapShotId() {
        return goodSnapShotId;
    }

    public void setGoodSnapShotId(Long goodSnapShotId) {
        this.goodSnapShotId = goodSnapShotId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public GoodSnapshot getGoodSnapshot() {
        return goodSnapshot;
    }

    public void setGoodSnapshot(GoodSnapshot goodSnapshot) {
        this.goodSnapshot = goodSnapshot;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", goodSnapShotId=" + goodSnapShotId +
                ", deliveryId=" + deliveryId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderType='" + orderType + '\'' +
                ", payType='" + payType + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", goodSnapshot=" + goodSnapshot +
                '}';
    }
}
