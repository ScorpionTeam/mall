package com.scoprion.mall.domain;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class AttrValueExt extends AttrValue {

    private Long attrValueId;

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    @Override
    public String toString() {
        return "AttrValueExt{" +
                "attrValueId=" + attrValueId +
                '}';
    }
}
