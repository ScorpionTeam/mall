package com.scoprion.mall.domain.good;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * Created on 2017/9/28.
 */
public class GoodSnapshot {

    //主键
    private Long id;

    //商品id
    @JSONField(name = "good_id")
    private Long goodId;

    //商品编号
    @JSONField(name = "good_no")
    private String goodNo;

    //商品快照编码
    @JSONField(name = "good_snapshot_no")
    private String goodSnapShotNo;

    //商品名称
    @JSONField(name = "good_name")
    private String goodName;

    //商品描述
    @JSONField(name = "good_description")
    private String goodDescription;

    //促销价
    private int promotion;

    //商品原价
    private int price;

    //折扣
    private int discount;

    //商品主图
    @JSONField(name = "main_image_url")
    private String mainImageUrl;

    @JSONField(name = "rich_content")
    private String richContent;

    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 商户ID
     */
    @JSONField(name = "seller_id")
    private Long sellerId;

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodSnapShotNo() {
        return goodSnapShotNo;
    }

    public void setGoodSnapShotNo(String goodSnapShotNo) {
        this.goodSnapShotNo = goodSnapShotNo;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRichContent() {
        return richContent;
    }

    public void setRichContent(String richContent) {
        this.richContent = richContent;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "GoodSnapshot{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", goodNo='" + goodNo + '\'' +
                ", goodSnapShotNo='" + goodSnapShotNo + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodDescription='" + goodDescription + '\'' +
                ", promotion=" + promotion +
                ", price=" + price +
                ", discount=" + discount +
                ", mainImageUrl='" + mainImageUrl + '\'' +
                ", richContent='" + richContent + '\'' +
                ", createDate=" + createDate +
                ", sellerId=" + sellerId +
                '}';
    }
}
