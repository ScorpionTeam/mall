package com.scoprion.mall.littlesoft.service.goods;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */



public interface WxGoodsService {

    /**
     * 获取商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
     PageResult findAll(Integer pageNo,Integer pageSize);

    /**
     * 根据商品id获取商品详情
     *
     * @param id
     * @return
     */
     BaseResult goodsDetail(Long id);
}
