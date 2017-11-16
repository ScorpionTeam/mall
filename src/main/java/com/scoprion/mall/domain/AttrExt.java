package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public class AttrExt extends Attr {

    /**
     * 排序
     */
    private int seq;

    private List<AttrValue> attrValueList;

    public List<AttrValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValue> attrValueList) {
        this.attrValueList = attrValueList;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "AttrExt{" +
                "seq=" + seq +
                ", attrValueList=" + attrValueList +
                '}';
    }
}
