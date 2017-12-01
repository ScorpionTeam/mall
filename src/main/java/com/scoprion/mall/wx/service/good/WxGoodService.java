package com.scoprion.mall.wx.service.good;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */

public interface WxGoodService {

    /**
     * 商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @return
     */
    PageResult findHomePage(Integer pageNo, Integer pageSize, Long categoryId);

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    BaseResult findById(Long goodId);

    /**
     * 查询商品评价列表
     *
     * @param pageNo
     * @param pageSize
     * @param goodId
     * @return
     */
    BaseResult findEstimate(Integer pageNo, Integer pageSize,
                            Long goodId);

    /**
     * 商品搜索
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @param categoryId
     * @return
     */
    PageResult findBySearchKey(Integer pageNo, Integer pageSize, Long categoryId, String searchKey);

    /**
     * 获取商品快照详情
     * @param orderId
     * @return
     */
    BaseResult findByGoodSnapshotDetail(Long orderId);
}
