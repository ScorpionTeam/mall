package com.scoprion.mall.domain.request;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-28 16:40
 */
public class OrderRequestParams extends PageRequestParams {

    /**
     * 支付类型0 支付宝ALI_PAY 微信WE_CHAT_PAY 信用卡CREDIT_PAY 储蓄卡DEPOSIT_PAY
     */
    private String payType;

    /**
     * 订单类型 PC_ORDER pc订单 MOBILE_ORDER手机订单 FREE_ORDER免费试用订单 SPELL_GROUP_ORDER拼团
     */
    private String orderType;

    /**
     * 状态 ALL 全部 UN_PAY 待付款 UN_DELIVERY 待发货 UN_RECEIVE 待收货 ALL_DONE已完成
     */
    private String orderStatus;

    /**
     * 模糊查询信息
     */
    private String searchKey;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 收件人手机号
     */
    private String phone;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商铺id
     */
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "OrderRequestParams{" +
                "payType='" + payType + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", searchKey='" + searchKey + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", phone='" + phone + '\'' +
                ", orderNo='" + orderNo + '\'' +
                "} " + super.toString();
    }
}
