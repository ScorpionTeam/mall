package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

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
     * 0秒杀
     * 1拼团
     * 2优选
     */
    private String activityType;

    /**
     * 状态
     * 0正常
     * 1删除
     */
    private String status;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
     * 商品列表
     */
    private List<ActivityGoods> activityGoodsList;

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

    public List<ActivityGoods> getActivityGoodsList() {
        return activityGoodsList;
    }

    public void setActivityGoodsList(List<ActivityGoods> activityGoodsList) {
        this.activityGoodsList = activityGoodsList;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityType='" + activityType + '\'' +
                ", status='" + status + '\'' +
                ", updateDate=" + updateDate +
                ", createDate=" + createDate +
                ", target='" + target + '\'' +
                ", activityGoodsList=" + activityGoodsList +
                '}';
    }
}
