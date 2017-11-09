package com.scoprion.mall.domain;

/**
 * @author by kunlun
 * @created on 2017/11/9.
 */
public class DeliveryExt extends Delivery {

    /**
     * 微信code
     */
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
