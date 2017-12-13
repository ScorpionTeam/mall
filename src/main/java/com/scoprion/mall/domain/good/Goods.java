package com.scoprion.mall.domain.good;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <商品实体/>
 * Created on 2017/9/28.
 *
 * @author adming
 */
public class Goods {

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键别名
     */
    private Long tgId;
    /**
     * 商品编码
     */
    @JSONField(name = "good_no")
    private String goodNo;

    /**
     * 类目id
     */
    @JSONField(name = "category_id")
    private Long categoryId;

    /**
     * 商品名称
     */
    @JSONField(name = "good_name")
    private String goodName;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 促销价格
     */
    private int promotion;

    /**
     * 价格
     */
    private int price;

    /**
     * 销量
     */
    @JSONField(name = "sale_volume")
    private int saleVolume;

    /**
     * 折扣
     */
    private int discount;

    /**
     * 库存
     */
    private int stock;

    /**
     * 上下架  1上架  0下架    默认上架
     */
    @JSONField(name = "on_sale")
    private String onSale;

    /**
     * 热销   1是   0否
     */
    private String hot;

    /**
     * 新品  1是  0否
     */
    @JSONField(name = "is_new")
    private String isNew;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;

    /**
     * 最后更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * 是否包邮  1是  0否
     */
    private String freight;

    /**
     * 运费
     */
    @JSONField(name = "freight_fee")
    private int freightFee;

    /**
     * 所属品牌
     */
    @JSONField(name = "brand_id")
    private Long brandId;

    /**
     * 商户ID
     */
    @JSONField(name = "seller_id")
    private Long sellerId;

    /**
     * 浏览量
     */
    @JSONField(name = "visit_total")
    private int visitTotal;

    /**
     * 主图地址
     */
    @JSONField(name = "main_image_url")
    private String mainImageUrl;

    /**
     * 商品描述富文本
     */
    @JSONField(name = "rich_content")
    private String richContent;
    /**
     * 商品状态 0正常，1删除
     */
    private String status;

    /**
     * 模糊搜索字段
     */
    private String seo;

    /**
     * 审核新商品创建  AUDITING 待审核/审核中    NOT_PASS_AUDIT审核未通过  PASS_AUDIT审核通过
     */
    private String audit;

    /**
     * 审核商品部通过的原因
     */
    private String reason;

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public String getRichContent() {
        return richContent;
    }

    public void setRichContent(String richContent) {
        this.richContent = richContent;
    }

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public Long getTgId() {
        return tgId;
    }

    public void setTgId(Long tgId) {
        this.tgId = tgId;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(int freightFee) {
        this.freightFee = freightFee;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", tgId=" + tgId +
                ", goodNo='" + goodNo + '\'' +
                ", categoryId=" + categoryId +
                ", goodName='" + goodName + '\'' +
                ", description='" + description + '\'' +
                ", promotion=" + promotion +
                ", price=" + price +
                ", saleVolume=" + saleVolume +
                ", discount=" + discount +
                ", stock=" + stock +
                ", onSale='" + onSale + '\'' +
                ", hot='" + hot + '\'' +
                ", isNew='" + isNew + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", freight='" + freight + '\'' +
                ", freightFee=" + freightFee +
                ", brandId=" + brandId +
                ", sellerId=" + sellerId +
                ", visitTotal=" + visitTotal +
                ", mainImageUrl='" + mainImageUrl + '\'' +
                ", richContent='" + richContent + '\'' +
                ", status='" + status + '\'' +
                ", seo='" + seo + '\'' +
                ", audit='" + audit + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
