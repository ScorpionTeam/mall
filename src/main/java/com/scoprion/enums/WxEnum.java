package com.scoprion.enums;

/**
 * @author by kunlun
 * @created on 2017/11/27.
 */
public enum WxEnum {
    SYSTEMERROR("SYSTEMERROR", "接口返回错误"),
    USER_ACCOUNT_ABNORMAL("USER_ACCOUNT_ABNORMAL", "请求退款失败"),
    NOTENOUGH("NOTENOUGH", "余额不足"),
    INVALID_TRANSACTIONID("INVALID_TRANSACTIONID", "无效的订单号"),
    PARAM_ERROR("PARAM_ERROR", "参数错误"),
    APPID_NOT_EXIST("APPID_NOT_EXIST", "APPID不存在"),
    MCHID_NOT_EXIST("MCHID_NOT_EXIST", "MCHID不存在"),
    APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配"),
    REQUIRE_POST_METHOD("REQUIRE_POST_METHOD", "请使用post方法"),
    SIGNERROR("SIGNERROR", "签名错误"),
    XML_FORMAT_ERROR("XML_FORMAT_ERROR", "XML格式错误");


    private String code;
    private String desc;

    WxEnum(String code, String desc) {
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
        for (WxEnum wxEnum : WxEnum.values()) {
            if (wxEnum.code.equalsIgnoreCase(key)) {
                return wxEnum.desc;
            }
        }
        return null;
    }


}
