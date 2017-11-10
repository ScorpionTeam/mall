package com.scoprion.mall.wx.service.point;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
public interface WxPointService {

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    BaseResult findByUserId(String userId);

    /**
     * 个人积分
     * @param id
     * @param userId
     * @return
     */
    BaseResult personalScore(Long id, String userId);

    /**
     * 等级划分
     * @param userId
     * @return
     */
    BaseResult grade(String userId);
}
