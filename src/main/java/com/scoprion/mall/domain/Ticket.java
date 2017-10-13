package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created on 2017/9/28.
 */
public class Ticket {

    private Long id;

    //优惠券编码
    private String ticketNo;

    //券名称
    private String ticketName;

    //金额
    private BigDecimal money;

    //类型  1平台  2商家
    private String type;

    //开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    //结束时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    //修改时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    //商家ID
    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketNo='" + ticketNo + '\'' +
                ", ticketName='" + ticketName + '\'' +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", sellerId=" + sellerId +
                '}';
    }
}
