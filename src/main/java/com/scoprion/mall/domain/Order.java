package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.math.BigDecimal;
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
     * 用户Id
     */
    private String userId;

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
     * 订单状态
     * 1 待付款
     * 2 待发货
     * 3 待收货
     * 4 已完成
     * 5 退款
     * 6 关闭
     * 7 待评价
     * 8 已评价
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
     * 买家留言
     */
    private String message;

    /**
     * 订单金额
     */
    private int orderFee;

    /**
     * 优惠金额
     */
    private int reduceFee;

    /**
     * 实付金额
     */
    private int paymentFee;

    /**
     * 商品金额
     */
    private int goodFee;

    /**
     * 运费金额
     */
    private int freightFee;

    /**
     * 下单时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 支付时间
     */
    private String payDate;

    /**
     * 发货时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryDate;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     * 收件人
     */
    private String recipients;

    /**
     * 收件人电话
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 数量
     */
    private int count;

    /**
     * 预付款id
     */
    private String prePayId;

    /**
     * 微信订单号
     */
    private String wxOrderNo;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 审核备注
     */
    private String remark;

    /**
     * 订单号
     */
    private String deliveryNo;

    /**
     * 是否使用优惠券
     */
    private String useTicket;

    /**
     * 优惠券id
     */
    private Long ticketId;

    public String getUseTicket() {
        return useTicket;
    }

    public void setUseTicket(String useTicket) {
        this.useTicket = useTicket;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWxOrderNo() {
        return wxOrderNo;
    }

    public void setWxOrderNo(String wxOrderNo) {
        this.wxOrderNo = wxOrderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getPrePayId() {
        return prePayId;
    }

    public void setPrePayId(String prePayId) {
        this.prePayId = prePayId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(int orderFee) {
        this.orderFee = orderFee;
    }

    public int getReduceFee() {
        return reduceFee;
    }

    public void setReduceFee(int reduceFee) {
        this.reduceFee = reduceFee;
    }

    public int getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(int paymentFee) {
        this.paymentFee = paymentFee;
    }

    public int getGoodFee() {
        return goodFee;
    }

    public void setGoodFee(int goodFee) {
        this.goodFee = goodFee;
    }

    public int getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(int freightFee) {
        this.freightFee = freightFee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", goodSnapShotId=" + goodSnapShotId +
                ", deliveryId=" + deliveryId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderType='" + orderType + '\'' +
                ", payType='" + payType + '\'' +
                ", message='" + message + '\'' +
                ", orderFee=" + orderFee +
                ", reduceFee=" + reduceFee +
                ", paymentFee=" + paymentFee +
                ", goodFee=" + goodFee +
                ", freightFee=" + freightFee +
                ", createDate=" + createDate +
                ", payDate='" + payDate + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", updateDate=" + updateDate +
                ", recipients='" + recipients + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", goodName='" + goodName + '\'' +
                ", count=" + count +
                ", prePayId='" + prePayId + '\'' +
                ", wxOrderNo='" + wxOrderNo + '\'' +
                ", goodId=" + goodId +
                ", remark='" + remark + '\'' +
                ", deliveryNo='" + deliveryNo + '\'' +
                '}';
    }
}
