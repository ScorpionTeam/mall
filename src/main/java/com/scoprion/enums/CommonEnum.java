package com.scoprion.enums;

/**
 * @author by kunlun
 * @created on 2017/11/28.
 */
public enum CommonEnum {

    NORMAL("NORMAL", "正常"),
    UN_NORMAL("UN_NORMAL", "非正常"),
    SECONDS_KILL("SECONDS_KILL", "秒杀"),
    SPELL_GROUP("SPELL_GROUP", "拼团"),
    FREE("FREE", "试用"),
    DEFAULT("DEFAULT", "默认地址"),
    UN_DEFAULT("UN_DEFAULT", "非默认地址"),
    SELLER("SELLER", "商家"),
    CUSTOMER("CUSTOMER", "客户"),
    UN_PAY("UN_PAY", "待付款"),
    UN_DELIVERY("UN_DELIVERY", "待发货"),
    UN_RECEIVE("UN_RECEIVE", "待收货"),
    ALL_ORDER("ALL_ORDER", "所有订单"),
    DONE("ALL_DONE", "已完成"),
    REFUND("REFUND", "退款"),
    CLOSING("CLOSING", "关闭"),
    UN_ESTIMATE("UN_ESTIMATE", "待评价"),
    ESTIMATE("ESTIMATE", "已评价"),
    PC_ORDER("PC_ORDER", "电脑订单"),
    MOBILE_ORDER("MOBILE_ORDER", "手机订单"),
    FREE_ORDER("FREE_ORDER", "免费试用订单"),
    ALI_PAY("ALI_PAY", "支付宝"),
    WE_CHAT_PAY("WE_CHAT_PAY", "微信"),
    CREDIT_PAY("CREDIT_PAY", "信用卡"),
    DEPOSIT_PAY("DEPOSIT_PAY", "储蓄卡"),
    USE_TICKET("USE_TICKET", "使用优惠券"),
    UN_USE_TICKET("UN_USE_TICKET", "不使用优惠券"),
    ON_SALE("ON_SALE", "上架"),
    OFF_SALE("OFF_SALE", "下架"),
    HOT("HOT", "热销"),
    NOT_HOT("NOT_HOT", "非热销"),
    IS_NEW("IS_NEW", "新品"),
    NOT_NEW("NOT_NEW", "非新品"),
    FREIGHT("FREIGHT", "包邮"),
    UN_FREIGHT("UN_FREIGHT", "不包邮"),
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女"),
    EXPIRE("EXPIRE", "已过期"),
    UN_START("UN_START", "未开始"),
    ON_THE_WAY("ON_THE_WAY", "进行中"),
    USE_POINT("USE_POINT", "使用积分"),
    NOT_USE_POINT("UN_USE_POINT", "不使用积分"),
    PRODUCE_POINT("PRODUCE_POINT", "产生积分"),
    CONSUME_POINT("CONSUME_POINT", "消费积分");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;


    CommonEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getValue(String key) {
        for (CommonEnum orderEnum : CommonEnum.values()) {
            if (orderEnum.getCode().equalsIgnoreCase(key)) {
                return orderEnum.getDesc();
            }
        }
        return null;
    }
}
