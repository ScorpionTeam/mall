package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author by hmy
 * @created on 2017/11/27/027.
 */
public class WxFreeOrder extends WxOrderRequestData {

    /**
     * 微信code
     */
    @JSONField(name = "wx_code")
    private String wxCode;

    @JSONField(name = "activity_id")
    private Long activityId;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "WxFreeOrder{" +
                "wxCode='" + wxCode + '\'' +
                ", activityId=" + activityId +
                '}';
    }
}
