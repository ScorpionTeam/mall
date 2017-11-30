package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by fk on 2017/11/29.
 */
public class WxGroupOrder extends WxOrderRequestData {
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
        return "WxGroupOrder{" +
                "wxCode='" + wxCode + '\'' +
                ", activityId=" + activityId +
                '}';
    }
}
