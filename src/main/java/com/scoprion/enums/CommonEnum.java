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
    PREFERRED("PREFERRED", "优选"),
    DEFAULT_ADDRESS("DEFAULT_ADDRESS", "默认地址"),
    UN_DEFAULT_ADDRESS("UN_DEFAULT_ADDRESS", "非默认地址"),
    PLATFORM("PLATFORM", "平台"),
    SELLER("SELLER", "商家"),
    CUSTOMER("CUSTOMER", "客户"),
    UN_PAY("UN_PAY", "待付款"),
    UN_DELIVERY("UN_DELIVERY", "待发货"),
    UN_RECEIVE("UN_RECEIVE", "待收货"),
    ALL("ALL", "所有"),
    DONE("DONE", "已完成"),
    REFUND("REFUND", "退款"),
    REFUNDING("REFUNDING", "退款中"),
    REFUND_SUCCESS("REFUND_SUCCESS", "退款成功"),
    REFUND_FAIL("REFUND_FAIL", "退款失败"),
    CLOSING("CLOSING", "关闭"),
    UN_ESTIMATE("UN_ESTIMATE", "待评价"),
    ESTIMATE("ESTIMATE", "已评价"),
    PC_ORDER("PC_ORDER", "电脑订单"),
    MOBILE_ORDER("MOBILE_ORDER", "手机订单"),
    SPELL_GROUP_ORDER("SPELL_GROUP_ORDER", "拼团订单"),
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
    ENTER("ENTER", "入驻"),
    QUITE("QUITE", "退出"),
    ADD("ADD", "增加"),
    SUBTRACT("SUBTRACT", "扣减"),
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女"),
    EXPIRE("EXPIRE", "已过期"),
    UN_START("UN_START", "未开始"),
    ON_THE_WAY("ON_THE_WAY", "进行中"),
    USE_POINT("USE_POINT", "使用积分"),
    NOT_USE_POINT("NOT_USE_POINT", "不使用积分"),
    PRODUCE_POINT("PRODUCE_POINT", "产生积分"),
    CONSUME_POINT("CONSUME_POINT", "消费积分"),
    UI("UI", "UI界面建议"),
    SALE_SERVICE("SALE_SERVICE", "售后服务"),
    EXPERIENCE("EXPERIENCE", "体验"),
    COMPLAIN("COMPLAIN", "投诉"),
    OTHER_SUGGEST("OTHER_SUGGEST", "其他建议"),
    PLATFORM_USER("PLATFORM_USER", "平台使用"),
    OTHER_USER("OTHER_USER", "其他地方使用"),
    PARENT_MENU("PARENT_MENU", "父菜单"),
    SUBMENU("SUBMENU", "子菜单"),
    LIMITED("LIMITED", "限量"),
    UN_LIMITED("UN_LIMITED", "不限量"),
    ALREADY_USED("ALREADY_USED", "已使用"),
    UNUSED("UNUSED", "未使用"),
    NOT_AUTH("NOT_AUTH", "未实名认证"),
    IS_AUTH("IS_AUTH", "认证通过"),
    NOT_PASS_AUTH("NOT_PASS_AUTH", "认证未通过"),
    USER_ADMIN("USER_ADMIN", "管理员用户"),
    USER_ORDINARY("USER_ORDINARY", "普通用户"),
    CUT("CUT", "裁剪"),
    NOT_CUT("NOT_CUT", "不裁剪"),
    WATER_REMARK("WATER_REMARK", "加水印"),
    NOT_WATER_REMARK("NOT_WATER_REMARK", "不加水印"),
    UNBIND_CATEGORY("UNBIND_CATEGORY", "未绑定类目"),
    UNBIND_ACTIVITY("UNBIND_ACTIVITY", "未绑定活动"),
    BIND_ACTIVITY("BIND_ACTIVITY", "已经绑定活动"),
    REFUSE("REFUSE", "拒绝"),
    AGREE("AGREE", "同意"),
    PARENT_CATEGORY("PARENT_CATEGORY", "一级类目"),
    CHILD_CATEGORY("CHILD_CATEGORY", "子类目"),
    ALL_DONE("ALL_DONE", "已完成"),
    LEVEL_SU_ADMIN("LEVEL_SU_ADMIN", "超级管理员等级"),
    LEVEL_ADMIN("LEVEL_ADMIN", "管理员角色等级"),
    LEVEL_ORDINARY("LEVEL_ORDINARY", "普通角色等级"),
    AUTH("AUTH", "JSONID:94268"),
    SALT("SALT", "&&salt"),
    AUDITING("AUDITING", "审核中"),
    NOT_PASS_AUDIT("NOT_PASS_AUDIT", "审核未通过"),
    PASS_AUDIT("PASS_AUDIT", "审核通过"),
    CLOSE_LEADER("CLOSE_LEADER", "管理员删除"),
    CLOSE("CLOSE", "关闭"),
    DELETE("DELETE", "删除状态");


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
