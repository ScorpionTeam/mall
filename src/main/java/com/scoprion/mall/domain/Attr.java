package com.scoprion.mall.domain;

/**
 * 属性关系
 *
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class Attr {

    /**
     * 属性id
     */
    private Long id;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性默认属性值id
     */
    private String defaultValueId;

    /**
     * 版本号
     */
    private int verNo;

    /**
     * 类目id
     */
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getDefaultValueId() {
        return defaultValueId;
    }

    public void setDefaultValueId(String defaultValueId) {
        this.defaultValueId = defaultValueId;
    }

    public int getVerNo() {
        return verNo;
    }

    public void setVerNo(int verNo) {
        this.verNo = verNo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "id=" + id +
                ", attrName='" + attrName + '\'' +
                ", defaultValueId='" + defaultValueId + '\'' +
                ", verNo=" + verNo +
                ", categoryId=" + categoryId +
                '}';
    }
}
