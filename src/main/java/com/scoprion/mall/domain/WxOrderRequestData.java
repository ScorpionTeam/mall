package com.scoprion.mall.domain;


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

    /** 1使用  0 不适用
     * 是否使用优惠券
     */
    private String useTicket;

    /**
     * 积分
     */
    private int point;

    /**
     * 商品总额
     */
    private int goodFee;

    /**
     * 实付金额
     */
    private int paymentFee;

    /**
     * 订单金额
     */
    private int orderFee;

    /**
     * 优惠金额
     */
    private int reduceFee;

    /**
     * 运费金额
     */
    private int freightFee;

    /**
     * 商品属性id
     */
    private Long attrId;

    /**
     * 商品属性值id
     */
    private Long attrValueId;

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(int orderFee) {
        this.orderFee = orderFee;
    }

    public int getGoodFee() {
        return goodFee;
    }

    public void setGoodFee(int goodFee) {
        this.goodFee = goodFee;
    }

    public int getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(int paymentFee) {
        this.paymentFee = paymentFee;
    }

    public int getReduceFee() {
        return reduceFee;
    }

    public void setReduceFee(int reduceFee) {
        this.reduceFee = reduceFee;
    }

    public int getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(int freightFee) {
        this.freightFee = freightFee;
    }

    public String getUseTicket() {
        return useTicket;
    }

    public void setUseTicket(String useTicket) {
        this.useTicket = useTicket;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
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
                ", useTicket='" + useTicket + '\'' +
                ", point=" + point +
                ", goodFee=" + goodFee +
                ", paymentFee=" + paymentFee +
                ", orderFee=" + orderFee +
                ", reduceFee=" + reduceFee +
                ", freightFee=" + freightFee +
                ", attrId=" + attrId +
                ", attrValueId=" + attrValueId +
                '}';
    }
}
