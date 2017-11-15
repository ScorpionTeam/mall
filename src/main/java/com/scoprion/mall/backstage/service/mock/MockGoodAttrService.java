package com.scoprion.mall.backstage.service.mock;

import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
public interface MockGoodAttrService {

    /**
     * 创建商品属性  属性值
     * @return
     */
    BaseResult add();

    /**
     * 查询商品规格属性  属性值
     * @param goodId
     * @return
     */
    BaseResult findAttr(Long goodId);
}
