package com.scoprion.mall.backstage.service.point;

import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/10/29.
 */
public interface PointService {

    /**
     * 查询我的积分
     *
     * @param userId
     * @return
     */
    BaseResult myPoint(Long userId);

}
