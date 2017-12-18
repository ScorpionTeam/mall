package com.scoprion.mall.domain;

import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.Goods;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-18 16:41
 */
public class MockGood {

    private GoodExt good;

    private List<MallImage> imageList;

    public GoodExt getGood() {
        return good;
    }

    public void setGood(GoodExt good) {
        this.good = good;
    }

    public List<MallImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<MallImage> imageList) {
        this.imageList = imageList;
    }
}
