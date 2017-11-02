package com.scoprion.mall.littlesoft.service.good;

import com.scoprion.result.BaseResult;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
public interface GoodWxService {

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    BaseResult goodDetail(Long goodId);
}
