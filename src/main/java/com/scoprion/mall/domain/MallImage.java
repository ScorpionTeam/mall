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
     * 证件照所属人id
     */
    @JSONField(name = "id_photo_owner_id")
    private Long idPhotoOwnerId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;
    /**
     * 图片地址
     */
    private String url;

    /**
     * 是否是富文本图片   默认 不是 UN_NORMAL    NORMAL 是
     */
    @JSONField(name = "is_rich_text")
    private String isRichText;


    /**
     * 文章id
     */
    @JSONField(name = "article_id")
    private Long articleId;
    /**
     * 活动id
     */
    @JSONField(name = "activity_id")
    private Long activityId;
    /**
     * 广告id
     */
    @JSONField(name = "banner_id")
    private Long bannerId;
    /**
     * 品牌id
     */
    @JSONField(name = "brand_id")
    private Long brandId;
    /**
     * 商品id
     */
    @JSONField(name = "good_id")
    private Long goodId;

    /**
     * 商品评价id
     */
    @JSONField(name = "estimate_id")
    private Long estimateId;

    public MallImage() {
    }

    public MallImage(String url) {
        this.url = url;
    }

    public MallImage(String url, String isRichText) {
        this.url = url;
        this.isRichText = isRichText;
    }

    public Long getIdPhotoOwnerId() {
        return idPhotoOwnerId;
    }

    public void setIdPhotoOwnerId(Long idPhotoOwnerId) {
        this.idPhotoOwnerId = idPhotoOwnerId;
    }

    public String getIsRichText() {
        return isRichText == null ? "UN_NORMAL" : isRichText;
    }

    public void setIsRichText(String isRichText) {
        this.isRichText = isRichText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
