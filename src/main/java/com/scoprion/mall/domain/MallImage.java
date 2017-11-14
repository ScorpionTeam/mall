package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <商城图片绑定关系实体>
 * @date 2017-11-08 10:13
 */
public class MallImage {

    /**
     * 主键
     */
    private Long id;
    /**
     * 关联id
     */
    private Long otherId;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 图片地址
     */
    private String url;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 活动id
     */
    private Long activityId;
    /**
     * 广告id
     */
    private Long bannerId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品评价id
     */
    private Long estimateId;

    public MallImage() {
    }

    public MallImage(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOtherId() {
        return otherId;
    }

    public void setOtherId(Long otherId) {
        this.otherId = otherId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(Long estimateId) {
        this.estimateId = estimateId;
    }

    @Override
    public String toString() {
        return "MallImage{" +
                "id=" + id +
                ", otherId=" + otherId +
                ", createDate=" + createDate +
                ", url='" + url + '\'' +
                ", articleId=" + articleId +
                ", activityId=" + activityId +
                ", bannerId=" + bannerId +
                ", brandId=" + brandId +
                ", goodId=" + goodId +
                ", estimateId=" + estimateId +
                '}';
    }
}
