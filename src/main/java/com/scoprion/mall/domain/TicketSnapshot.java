package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <优惠券快照>
 * @date 2017-11-15 09:50
 */
public class TicketSnapshot {

    private Long id;


    /**
     * 优惠券编码
     */
    @JSONField(name = "ticket_no")
    private String ticketNo;

    /**
     * 券名称
     */
    @JSONField(name = "ticket_name")
    private String ticketName;

    /**
     * 类型  1平台  2商家
     */
    private String type;
    /**
     * 说明
     */
    private String content;
    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "end_date")
    private Date endDate;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "update_date")
    private Date updateDate;

    /**
     * 总金额
     */
    private int money;

    /**
     * 满减金额
     */
    @JSONField(name = "reduce_money")
    private int reduceMoney;
    /**
     * 数量
     */
    private int num;

    /**
     * 使用范围 使用范围 平台0通用，1其他类型 默认0
     */
    @JSONField(name = "use_range")
    private String useRange;

    /**
     * 是否限量 0是 1否
     */
    @JSONField(name = "num_limit")
    private String numLimit;

    /**
     * 优惠券id
     */
    @JSONField(name = "ticket_id")
    private Long ticketId;

    /**
     * 状态 1 删除 0正常
     */
    private String status;

    /**
     * 使用时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "use_date")
    private Date useDate;

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public int getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(int reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public String getUseRange() {
        return useRange;
    }

    public void setUseRange(String useRange) {
        this.useRange = useRange;
    }

    public String getNumLimit() {
        return numLimit;
    }

    public void setNumLimit(String numLimit) {
        this.numLimit = numLimit;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketSnapshot{" +
                "id=" + id +
                ", ticketNo='" + ticketNo + '\'' +
                ", ticketName='" + ticketName + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", money=" + money +
                ", num=" + num +
                ", useRange='" + useRange + '\'' +
                ", numLimit='" + numLimit + '\'' +
                ", ticketId=" + ticketId +
                ", status='" + status + '\'' +
                '}';
    }
}
