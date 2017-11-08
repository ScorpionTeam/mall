package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
public class ActivityExt extends Activity {

    /**
     * 商品id集合
     */
    private List<Long> goodIdList;

    public List<Long> getGoodIdList() {
        return goodIdList;
    }

    public void setGoodIdList(List<Long> goodIdList) {
        this.goodIdList = goodIdList;
    }

    @Override
    public String toString() {
        return "ActivityExt{" +
                "goodIdList=" + goodIdList +
                '}';
    }

}
