package com.scoprion.mall.backstage.service.order;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/9/29.
 */
public interface OrderService {

    /**
     * 查询用户的订单列表
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @param status   订单状态
     * @param userId   用户id
     * @return
     */
    PageResult findByPage(int pageNo, int pageSize, String status, Long userId);

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @return
     */
    BaseResult  orderConfirm(Long goodId, Long deliveryId,String ipAddress) throws Exception;

    BaseResult mockList();

    /**
     *  我的订单
     * @param pageNo
     * @param pageSize
     * @param userId
     * @param status
     * @return
     */
    PageResult myOrder(int pageNo,int pageSize,Long userId,String status);
}
