package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <商品图片实体>
 * @date 2017-11-08 10:13
 */
public class GoodsImage {

    /**
     * 主键
     */
    private Long id;
    /**
     * 商品ID，
     */
    private Long goodId;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 商品图片地址
     */
    private String imgUrl;

    public GoodsImage() {
    }

    public GoodsImage(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "GoodsImage{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", createDate=" + createDate +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
