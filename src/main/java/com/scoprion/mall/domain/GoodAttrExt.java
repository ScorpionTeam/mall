package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class GoodAttrExt extends GoodAttr {

    private List<AttrExt> attrExts;

    public List<AttrExt> getAttrExts() {
        return attrExts;
    }

    public void setAttrExts(List<AttrExt> attrExts) {
        this.attrExts = attrExts;
    }

    @Override
    public String toString() {
        return "GoodAttrExt{" +
                "attrExts=" + attrExts +
                '}';
    }
}
