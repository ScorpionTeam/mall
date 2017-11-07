package com.scoprion.mall.wx.service.order;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
public interface OrderWxService {
    /**
     * 分页获取我的订单列表
     *
     * @param userId
     * @param status
     * 0 全部
     * 1 待付款
     * 2 待发货
     * 3 待收货
     * 4 已完成
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult getMyOrder(Long userId, String status, Integer pageNo, Integer pageSize);

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @param ipAdress
     * @param purchase:订单量
     * @return
     */
    BaseResult ordered(Long goodId,Long deliveryId,String ipAdress,Integer purchase) throws Exception;
}
