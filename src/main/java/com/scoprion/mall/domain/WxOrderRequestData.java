package com.scoprion.mall.domain;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
public class WxOrderRequestData {

    /**
     * 商品id
     */
    @JSONField(name = "good_id")
    private Long goodId;

    /**
     * 配送地址id
     */
    @JSONField(name = "delivery_id")
    private Long deliveryId;

    /**
     * 商家名称
     */
    @JSONField(name = "seller_name")
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
     * 订单类型 1pc订单  2手机订单
     */
    @JSONField(name = "order_type")
    private String orderType;
    /**
     * 优惠券id
     */
    private Long ticket;

    /**
     * 1使用  0 不适用
     * 是否使用优惠券
     */
    @JSONField(name = "use_ticket")
    private String useTicket;

    /**
     * 积分
     */
    private int point;

    /**
     * 商品总额
     */
    @JSONField(name = "good_fee")
    private int goodFee;

    /**
     * 实付金额
     */
    @JSONField(name = "payment_fee")
    private int paymentFee;

    /**
     * 订单金额
     */
    @JSONField(name = "order_fee")
    private int orderFee;

    /**
     * 优惠金额
     */
    @JSONField(name = "reduce_fee")
    private int reduceFee;

    /**
     * 运费金额
     */
    @JSONField(name = "freight_fee")
    private int freightFee;

    @JSONField(name = "wx_code")
    private String wxCode;

    @JSONField(name = "ip_address")
    private String ipAddress;


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

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

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "WxOrderRequestData{" +
                "goodId=" + goodId +
                ", deliveryId=" + deliveryId +
                ", sellerName='" + sellerName + '\'' +
                ", count=" + count +
                ", message='" + message + '\'' +
                ", orderType='" + orderType + '\'' +
                ", ticket=" + ticket +
                ", useTicket='" + useTicket + '\'' +
                ", point=" + point +
                ", goodFee=" + goodFee +
                ", paymentFee=" + paymentFee +
                ", orderFee=" + orderFee +
                ", reduceFee=" + reduceFee +
                ", freightFee=" + freightFee +
                ", wxCode='" + wxCode + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
