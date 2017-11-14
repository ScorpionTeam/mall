package com.scoprion.mall.domain;


import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
public class ActivityGoods extends Goods {

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 商品ID
     */
    private Long goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 创建时间
     */
    private Date createDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getGoodName() {
        return goodName;
    }

    @Override
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toString() {
        return "ActivityGoods{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", goodId=" + goodId +
                ", createDate=" + createDate +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
