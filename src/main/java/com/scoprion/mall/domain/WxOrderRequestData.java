package com.scoprion.mall.domain;

import java.math.BigDecimal;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
public class WxOrderRequestData {

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 配送地址id
     */
    private Long deliveryId;

    /**
     * 商家名称
     */
    private String sellerName;

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
    private Long ticket;

    /**
     * 是否使用优惠券
     */
    private boolean useTicket;

    /**
     * 积分
     */
    private int point;

    /**
     * 商品总额
     */
    private BigDecimal goodPrice;

    /**
     * 实付金额
     */
    private BigDecimal totalFee;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public boolean isUseTicket() {
        return useTicket;
    }

    public void setUseTicket(boolean useTicket) {
        this.useTicket = useTicket;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "WxOrderRequestData{" +
                "goodId=" + goodId +
                ", deliveryId=" + deliveryId +
                ", sellerName='" + sellerName + '\'' +
                ", count=" + count +
                ", message='" + message + '\'' +
                ", ticket=" + ticket +
                ", useTicket=" + useTicket +
                ", point=" + point +
                ", goodPrice=" + goodPrice +
                ", totalFee=" + totalFee +
                '}';
    }
}
