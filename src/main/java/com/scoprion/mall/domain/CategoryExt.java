package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/9/28.
 */
public class CategoryExt extends Category {
    private List<Category> childList;

    public List<Category> getChildList() {
        return childList;
    }

    public void setChildList(List<Category> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "CategoryExt{" +
                "childList=" + childList +
                "} " + super.toString();
    }
}
