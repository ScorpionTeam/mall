package com.scoprion.mall.domain;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class GoodAttr {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 版本号
     */
    private int verNo;

    /**
     * 是否可见  0不展示   1展示
     */
    private String attrVisible;

    /**
     * 默认属性值
     */
    private String defaultValue;

    /**
     * 排序
     */
    private int seq;

    /**
     * 0 产品参数  1 属性值
     */
    private String attrType;

    /**
     * 0 多选  1 单选  2 下拉  3 输入
     */
    private String displayType;

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

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getVerNo() {
        return verNo;
    }

    public void setVerNo(int verNo) {
        this.verNo = verNo;
    }

    public String getAttrVisible() {
        return attrVisible;
    }

    public void setAttrVisible(String attrVisible) {
        this.attrVisible = attrVisible;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    @Override
    public String toString() {
        return "GoodAttr{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", attrId=" + attrId +
                ", attrName='" + attrName + '\'' +
                ", verNo=" + verNo +
                ", attrVisible='" + attrVisible + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", seq=" + seq +
                ", attrType='" + attrType + '\'' +
                ", displayType='" + displayType + '\'' +
                '}';
    }
}
