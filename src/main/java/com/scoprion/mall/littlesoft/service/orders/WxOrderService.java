package com.scoprion.mall.littlesoft.service.orders;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */
public interface WxOrderService {

    /**
     *订单列表
     * @param pageNo
     * @param pageSize
     * @param userId
     * @param orderStatus
     * @return
     */
    PageResult findByCondition (Integer pageNo,Integer pageSize,Long userId,String orderStatus);


    BaseResult findByCondition(Long orderId);

}
