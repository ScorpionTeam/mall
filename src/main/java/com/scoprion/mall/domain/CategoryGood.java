package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-29 10:49
 */
public class CategoryGood {
    /**
     * 主键
     */
    private Long id;


    /**
     * 类目id
     */
    @JSONField(name = "category_id")
    private Long categoryId;

    /**
     * 商品id
     */
    @JSONField(name = "good_id")
    private Long goodId;

    /**
     * 创建时间
     */
    @JSONField(name = "create_date")
    private Long createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CategoryGood{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", goodId=" + goodId +
                ", createDate=" + createDate +
                '}';
    }
}
