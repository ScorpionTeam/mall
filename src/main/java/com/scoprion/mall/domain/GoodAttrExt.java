package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class GoodAttrExt extends GoodAttr {

    private List<Attr> attrList;

    public List<Attr> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<Attr> attrList) {
        this.attrList = attrList;
    }

    @Override
    public String toString() {
        return "GoodAttrExt{" +
                "attrList=" + attrList +
                '}';
    }
}
