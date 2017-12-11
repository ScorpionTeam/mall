package com.scoprion.mall.domain.order;

import com.alibaba.fastjson.annotation.JSONField;

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
     * 商铺Id
     */
    @JSONField(name = "seller_id")
    private Long sellerId;


    /**
     * 用户Id
     */
    @JSONField(name = "user_id")
    private String userId;

    /**
     * 订单编号
     */
    @JSONField(name = "order_no")
    private String orderNo;

    /**
     * 商品快照id
     */
    @JSONField(name = "good_snapshot_id")
    private Long goodSnapShotId;

    /**
     * 配送地址id
     */
    @JSONField(name = "delivery_id")
    private Long deliveryId;

    /**
     * 运单号
     */
    @JSONField(name = "delivery_no")
    private String deliveryNo;

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
    @JSONField(name = "order_status")
    private String orderStatus;

    /**
     * 订单类型 1pc订单  2手机订单
     */
    @JSONField(name = "order_type")
    private String orderType;

    /**
     * 支付类型
     * 0 支付宝
     * 1 微信
     * 2 信用卡
     * 3 储蓄卡
     */
    @JSONField(name = "pay_type")
    private String payType;

    /**
     * 买家留言
     */
    private String message;

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
     * 实付金额
     */
    @JSONField(name = "payment_fee")
    private int paymentFee;

    /**
     * 商品金额
     */
    @JSONField(name = "good_fee")
    private int goodFee;

    /**
     * 运费金额
     */
    @JSONField(name = "freight_fee")
    private int freightFee;
    /**
     * 退款金额
     */
    @JSONField(name = "refund_fee")
    private int refundFee;

    /**
     * 申请退款时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "refund_date")
    private Date refundDate;
    /**
     * 下单时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;

    /**
     * 支付时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "pay_date")
    private String payDate;

    /**
     * 发货时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "delivery_date")
    private Date deliveryDate;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "update_date")
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
    @JSONField(name = "post_code")
    private String postCode;

    /**
     * 商品名称
     */
    @JSONField(name = "good_name")
    private String goodName;

    /**
     * 数量
     */
    private int count;

    /**
     * 预付款id
     */
    @JSONField(name = "prepay_id")
    private String prepayId;

    /**
     * 微信订单号
     */
    @JSONField(name = "wx_order_no")
    private String wxOrderNo;

    /**
     * 商品id
     */
    @JSONField(name = "good_id")
    private Long goodId;

    /**
     * 审核备注
     */
    private String remark;

    /**
     * 是否使用优惠券  USE_TICKET使用优惠券，UN_USE_TICKET不使用优惠券
     */
    @JSONField(name = "use_ticket")
    private String useTicket;

    /**
     * USE_POINT", "使用积分 NOT_USE_POINT", "不使用积分
     */
    @JSONField(name = "use_point")
    private String usePoint;

    /**
     * 优惠券id
     */
    @JSONField(name = "ticket_id")
    private Long ticketId;

    /**
     * 此订单消耗的积分，不消耗为0或者null
     */
    @JSONField(name = "operate_point")
    private Integer operatePoint;

    /**
     * 发货信息id
     */
    @JSONField(name = "send_good_id")
    private Long sendGoodId;

    public Order() {
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    public String getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(String usePoint) {
        this.usePoint = usePoint;
    }

    public Integer getOperatePoint() {
        return operatePoint == null ? 0 : operatePoint;
    }

    public void setOperatePoint(Integer operatePoint) {
        this.operatePoint = operatePoint;
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

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public Long getSendGoodId() {
        return sendGoodId;
    }

    public void setSendGoodId(Long sendGoodId) {
        this.sendGoodId = sendGoodId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", goodSnapShotId=" + goodSnapShotId +
                ", deliveryId=" + deliveryId +
                ", deliveryNo='" + deliveryNo + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderType='" + orderType + '\'' +
                ", payType='" + payType + '\'' +
                ", message='" + message + '\'' +
                ", orderFee=" + orderFee +
                ", reduceFee=" + reduceFee +
                ", paymentFee=" + paymentFee +
                ", goodFee=" + goodFee +
                ", freightFee=" + freightFee +
                ", refundFee=" + refundFee +
                ", refundDate=" + refundDate +
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
                ", prepayId='" + prepayId + '\'' +
                ", wxOrderNo='" + wxOrderNo + '\'' +
                ", goodId=" + goodId +
                ", remark='" + remark + '\'' +
                ", useTicket='" + useTicket + '\'' +
                ", usePoint='" + usePoint + '\'' +
                ", ticketId=" + ticketId +
                ", operatePoint=" + operatePoint +
                ", sendGoodId=" + sendGoodId +
                '}';
    }
}
