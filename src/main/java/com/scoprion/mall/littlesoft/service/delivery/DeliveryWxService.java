package com.scoprion.mall.littlesoft.service.delivery;

import com.scoprion.result.PageResult;

/**
 * Created by admin1 on 2017/11/1.
 */
public interface DeliveryWxService {

    /**
     * 查询用户收获地址列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult DeliveryList(Long userId, Integer pageNo, Integer pageSize);
}
