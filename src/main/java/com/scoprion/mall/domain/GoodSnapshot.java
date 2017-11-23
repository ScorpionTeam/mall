package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created on 2017/9/28.
 */
public class GoodSnapshot {

    //主键
    private Long id;

    //商品id
    private Long goodId;

    //商品编号
    private String goodNo;

    //商品快照编码
    private String goodSnapShotNo;

    //商品名称
    private String goodName;

    //商品描述
    private String goodDescription;

    //促销价
    private Integer promotion;

    //商品原价
    private Integer price;

    //折扣
    private int discount;

    //商品主图
    private String mainImgUrl;

    private String richContent;

    /**
     * 商品属性id
     */
    private Long attrId;

    /**
     * 商品属性值id
     */
    private Long attrValueId;

    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


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

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
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

    @Override
    public String toString() {
        return "GoodSnapshot{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", goodSnapShotNo='" + goodSnapShotNo + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodDescription='" + goodDescription + '\'' +
                ", promotion=" + promotion +
                ", richContent=" + richContent +
                ", price=" + price +
                ", discount=" + discount +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
