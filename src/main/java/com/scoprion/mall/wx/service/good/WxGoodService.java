package com.scoprion.mall.wx.service.good;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

public interface WxGoodService {

    /**
     * 商品列表
     *
     * @param pageNo
     * @param PageSize
     * @return
     */
    PageResult findOnline(int pageNo, int PageSize);

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    BaseResult findById(Long goodId);


}
