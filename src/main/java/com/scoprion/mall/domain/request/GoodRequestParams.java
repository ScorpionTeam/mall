package com.scoprion.mall.domain.request;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-28 14:53
 */
public class GoodRequestParams extends PageRequestParams {
    /**
     * 模糊信息
     */
    private String searchKey;
    /**
     * 商品编号
     */
    private String goodNo;
    /**
     * 上下架 上下ON_SALE", "上架 OFF_SALE", "下架"),
     */
    private String saleStatus;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 类目
     */
    private Long categoryId;
    /**
     * 热销 是否热销 HOT", "热销 NOT_HOT", "非热销
     */
    private String isHot;
    /**
     * 新品 是否新品 IS_NEW", "新品 NOT_NEW", "非新品
     */
    private String isNew;
    /**
     * 包邮 是否包邮 FREIGHT包邮 UN_FREIGHT 不包邮
     */
    private String isFreight;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 商铺id
     */
    private Long sellerId;

    /**
     * UNBIND_CATEGORY, 未绑定类目列表
     * UNBIND_ACTIVITY, 未绑定活动列表
     * BIND_ACTIVITY, "已经绑定活动列表
     * 不传（null）无限制条件
     */
    private String type;

    /**
     * 审核状态 AUDITING 待审核/审核中
     * NOT_PASS_AUDIT 审核未通过
     * PASS_AUDIT 审核通过
     */
    private String audit;


    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsFreight() {
        return isFreight;
    }

    public void setIsFreight(String isFreight) {
        this.isFreight = isFreight;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "GoodRequestParams{" +
                "searchKey='" + searchKey + '\'' +
                ", goodNo='" + goodNo + '\'' +
                ", saleStatus='" + saleStatus + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", categoryId=" + categoryId +
                ", isHot='" + isHot + '\'' +
                ", isNew='" + isNew + '\'' +
                ", isFreight='" + isFreight + '\'' +
                ", brandId=" + brandId +
                ", activityId=" + activityId +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }
}
