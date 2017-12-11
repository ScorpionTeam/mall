package com.scoprion.mall.backstage.service.shop;

import com.scoprion.result.PageResult;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */
public interface ShopService {

    /**
     * 商铺列表
     * @param pageNo
     * @param pageSize
     * @param audit
     * @return
     */
    PageResult listPage(Integer pageNo, Integer pageSize,String audit);
}
