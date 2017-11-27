package com.scoprion.enums;

/**
 * @author by kunlun
 * @created on 2017/11/27.
 */
public enum OrderEnum {

    UN_PAY("UN_PAY", "待付款"),
    UN_DELIVERY("UN_DELIVERY", "待发货"),
    UN_RECEIVE("UN_RECEIVE", "待收货"),
    DONE("ALL_DONE", "已完成"),
    REFUND("REFUND", "退款"),
    CLOSING("CLOSING", "关闭"),
    UN_ESTIMATE("UN_ESTIMATE", "待评价"),
    ESTIMATE("ESTIMATE", "已评价"),
    PC_ORDER("PC_ORDER", "电脑订单"),
    MOBILE_ORDER("MOBILE_ORDER", "手机订单"),
    FREE_ORDER("FREE_ORDER", "免费试用订单"),
    ALI_PAY("ALI_PAY", "支付宝"),
    WE_CHAT_PAY("WECHAT_PAY", "微信"),
    CREDIT_PAY("CREDIT_PAY", "信用卡"),
    DEPOSIT_PAY("DEPOSIT_PAY", "储蓄卡"),
    USE_TICKET("USE_TICKET", "使用优惠券"),
    UN_USE_TICKET("UN_USE_TICKET", "不使用优惠券");


    /**
     * 操作 key
     */
    private String optK;

    /**
     * 操作 value
     */
    private String optV;

    OrderEnum(String optK, String optV) {
        this.optK = optK;
        this.optV = optV;
    }

    public String getOptK() {
        return optK;
    }

    public void setOptK(String optK) {
        this.optK = optK;
    }

    public String getOptV() {
        return optV;
    }

    public void setOptV(String optV) {
        this.optV = optV;
    }

    public static String getValue(String key) {
        for (OrderEnum orderEnum : OrderEnum.values()) {
            if (orderEnum.getOptK().equalsIgnoreCase(key)) {
                return orderEnum.getOptV();
            }
        }
        return null;
    }
}
