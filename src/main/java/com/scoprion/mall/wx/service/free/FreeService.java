package com.scoprion.mall.wx.service.free;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
public interface FreeService {

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findAll(int pageNo, int pageSize);

    /**
     * 参加试用活动
     *
     * @param activityId
     * @return
     */
    BaseResult apply(Long activityId,String wxCode);
}
