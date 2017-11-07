package com.scoprion.mall.backstage.service.order;

import com.scoprion.mall.domain.Order;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/9/29.
 *
 * @author ycj
 */
public interface OrderService {

    /**
     * 订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param payType     支付类型0 支付宝1 微信2 信用卡3 储蓄卡
     * @param orderType   订单类型 1pc订单  2手机订单
     * @param orderStatus 状态  0 全部  1 待付款   2 待发货  3 待收货 4 已完成
     * @param searchKey   模糊查询信息
     * @param startDate   开始时间
     * @param endDate     结束时间
     * @return
     */
    PageResult listPage(Integer pageNo, Integer pageSize, String payType, String orderType,
                        String orderStatus, String searchKey, String startDate, String endDate);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    BaseResult modify(Order order);
}
