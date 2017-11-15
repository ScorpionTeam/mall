package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <优惠券-用户关联关系>
 * @date 2017-11-14 15:44
 */
public class TicketUser {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 优惠券id
     */
    private Long ticketId;
    /**
     * 优惠券数量
     */
    private int num;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 使用日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date useDate;

    /**
     * 使用状态 0未使用 1已使用
     */
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TicketUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticketId=" + ticketId +
                ", num=" + num +
                ", createDate=" + createDate +
                ", useDate=" + useDate +
                ", type='" + type + '\'' +
                '}';
    }
}
