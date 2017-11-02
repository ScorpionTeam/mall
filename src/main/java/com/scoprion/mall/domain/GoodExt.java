package com.scoprion.mall.domain;
import java.math.BigDecimal;

/**
 * Created by admin1 on 2017/11/1.
 */
public class GoodExt {

    /**
     * 活动Id
     */
    private Long activityId;


    /**
     * 活动类型
     * 0秒杀
     * 1拼团
     * 2优选
     */
    private String activityType;

    /**
     * 目标
     * 0 小程序
     * 1 app
     * 2 网站
     */
    private String target;

    //商品Id
    private Long goodId;

    //商品编码
    private String goodNo;

    //类目id
    private Long categoryId;

    //商品名称
    private String goodName;

    //商品描述
    private String description;

    //促销价格
    private BigDecimal promotion;

    //价格
    private BigDecimal price;

    //销量
    private int saleVolume;

    //折扣
    private int discount;

    //库存
    private int stock;

    //上下架  1上架  0下架    默认上架
    private String isOnSale;

    //热销   1是   0否
    private String isHot;

    //新品  1是  0否
    private String isNew;

    //是否包邮  1是  0否
    private String isFreight;

    //所属品牌
    private Long brandId;

    //商户ID
    private Long sellerId;

    //商户名称
    private Long sellerName;

    //浏览量
    private int visitTotal;

    //主图地址
    private String mainImgUrl;


    public Long getSellerName() {
        return sellerName;
    }

    public void setSellerName(Long sellerName) {
        this.sellerName = sellerName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }


    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPromotion() {
        return promotion;
    }

    public void setPromotion(BigDecimal promotion) {
        this.promotion = promotion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(int saleVolume) {
        this.saleVolume = saleVolume;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(String isOnSale) {
        this.isOnSale = isOnSale;
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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getVisitTotal() {
        return visitTotal;
    }

    public void setVisitTotal(int visitTotal) {
        this.visitTotal = visitTotal;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    @Override
    public String toString() {
        return "GoodExt{" +
                "activityId=" + activityId +
                ", activityType='" + activityType + '\'' +
                ", target='" + target + '\'' +
                ", goodId=" + goodId +
                ", goodNo='" + goodNo + '\'' +
                ", categoryId=" + categoryId +
                ", goodName='" + goodName + '\'' +
                ", description='" + description + '\'' +
                ", promotion=" + promotion +
                ", price=" + price +
                ", saleVolume=" + saleVolume +
                ", discount=" + discount +
                ", stock=" + stock +
                ", isOnSale='" + isOnSale + '\'' +
                ", isHot='" + isHot + '\'' +
                ", isNew='" + isNew + '\'' +
                ", isFreight='" + isFreight + '\'' +
                ", brandId=" + brandId +
                ", sellerId=" + sellerId +
                ", sellerName=" + sellerName +
                ", visitTotal=" + visitTotal +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                '}';
    }
}
