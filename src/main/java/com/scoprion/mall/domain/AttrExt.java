package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class AttrExt extends Attr {

    private List<AttrValue> attrValueList;

    public List<AttrValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValue> attrValueList) {
        this.attrValueList = attrValueList;
    }

    @Override
    public String toString() {
        return "AttrExt{" +
                "attrValueList=" + attrValueList +
                '}';
    }
}
