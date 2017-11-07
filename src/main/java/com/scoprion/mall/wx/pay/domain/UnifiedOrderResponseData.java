package com.scoprion.mall.wx.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@XStreamAlias("xml")
public class UnifiedOrderResponseData {

    /**
     * 返回状态码
     * SUCCESS/FAIL
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 业务结果
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     */
    private String err_code_desc;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 预支付交易会话标识
     */
    private String prepay_id;

    /**
     * 二维码链接
     */
    private String code_url;

    private String paySign;

    private String timeStamp;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_desc() {
        return err_code_desc;
    }

    public void setErr_code_desc(String err_code_desc) {
        this.err_code_desc = err_code_desc;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "UnifiedOrderResponseData{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_desc='" + err_code_desc + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", code_url='" + code_url + '\'' +
                ", paySign='" + paySign + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
