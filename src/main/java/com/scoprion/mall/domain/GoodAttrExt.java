package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class GoodAttrExt extends AttrExt {

    /**
     * 属性集合
     */
    private List<AttrExt> attrExtList;

    public List<AttrExt> getAttrExtList() {
        return attrExtList;
    }

    public void setAttrExtList(List<AttrExt> attrExtList) {
        this.attrExtList = attrExtList;
    }

    @Override
    public String toString() {
        return "GoodAttrExt{" +
                "attrExtList=" + attrExtList +
                '}';
    }
}
