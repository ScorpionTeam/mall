package com.scoprion.mall.domain;

/**
 * 属性值
 *
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class AttrValue {

    /**
     * 属性值id
     */
    private Long id;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名称
     */
    private String attrValueName;

    /**
     * 属性值
     */
    private String attrValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    public String toString() {
        return "AttrValue{" +
                "id=" + id +
                ", attrId=" + attrId +
                ", attrValueName='" + attrValueName + '\'' +
                ", attrValue='" + attrValue + '\'' +
                '}';
    }
}
