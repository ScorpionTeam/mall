package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/10/10.
 */
public class Activity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;


    /**
     * 活动类型
     * 活动类型 SECONDS_KILL 秒杀 SPELL_GROUP 拼团 PREFERRED 优选 FREE试用
     */
    @JSONField(name = "activity_type")
    private String activityType;

    /**
     * 状态
     * NORMAL正常  UN_NORMAL 非正常 EXPIRE已过期
     */
    private String status;
    /**
     * 库存别名
     */
    private String actStatus;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "update_date")
    private Date updateDate;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;

    /**
     * 目标
     * 0 小程序
     * 1 app
     * 2 网站
     * 3 全部
     */
    private String target;


    /**
     * 参加活动人数
     */
    private Integer num;


    /**
     * 活动开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "start_date")
    private Date startDate;

    /**
     * 活动结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "end_date")
    private Date endDate;

    /**
     * 折扣价  80表示80%
     */
    private Integer discount;

    /**
     * 折扣价别名
     */
    private Integer actDiscount;

    /**
     * 活动描述
     */
    private String describes;

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public Integer getActDiscount() {
        return actDiscount;
    }

    public void setActDiscount(Integer actDiscount) {
        this.actDiscount = actDiscount;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityType='" + activityType + '\'' +
                ", status='" + status + '\'' +
                ", actStatus='" + actStatus + '\'' +
                ", updateDate=" + updateDate +
                ", createDate=" + createDate +
                ", target='" + target + '\'' +
                ", num=" + num +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discount=" + discount +
                ", actDiscount=" + actDiscount +
                ", describes='" + describes + '\'' +
                '}';
    }
}
