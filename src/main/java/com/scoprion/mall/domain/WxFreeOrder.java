package com.scoprion.mall.domain;

/**
 * @author by hmy
 * @created on 2017/11/27/027.
 */
public class WxFreeOrder extends WxOrderRequestData {

    /**
     * 微信code
     */
    private String wxCode;

    /**
     * 活动商品id
     */
    private Long activityGoodId;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Long getActivityGoodId() {
        return activityGoodId;
    }

    public void setActivityGoodId(Long activityGoodId) {
        this.activityGoodId = activityGoodId;
    }

    @Override
    public String toString() {
        return "WxFreeOrder{" +
                "wxCode='" + wxCode + '\'' +
                ", activityGoodId=" + activityGoodId +
                '}';
    }
}
