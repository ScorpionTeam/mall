package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author by hmy
 * @created on 2017/11/14/014.
 */
public class TicketExt extends Ticket {

    /**
     * 用户id
     */
    @JSONField(name = "user_id")
    private String userId;

    /**
     * 是否过期
     */
    private String expire;
    /**
     * 用户领取优惠券时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "TicketExt{" +
                "userId='" + userId + '\'' +
                ", expire='" + expire + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
