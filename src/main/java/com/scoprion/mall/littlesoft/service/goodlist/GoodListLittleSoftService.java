package com.scoprion.mall.littlesoft.service.goodlist;

import com.scoprion.result.PageResult;

/**
 * Created by admin1 on 2017/11/1.
 */
public interface GoodListLittleSoftService {

    /**
     * 获取商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param type   类型：0 秒杀, 1 拼团, 2精选
     * @return
     */
    PageResult getGoodList(Integer pageNo,Integer pageSize,String type);
}
