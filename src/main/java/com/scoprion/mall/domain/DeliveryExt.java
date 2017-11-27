package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author by kunlun
 * @created on 2017/11/9.
 */
public class DeliveryExt extends Delivery {

    /**
     * 微信code
     */
    @JSONField(name = "wx_code")
    private String wxCode;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    @Override
    public String toString() {
        return "DeliveryExt{" +
                "wxCode='" + wxCode + '\'' +
                '}';
    }
}
