package com.scoprion.mall.wx.service.order;

import com.scoprion.mall.domain.Estimate;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */
public interface WxOrderService {

    /**
     * 我的订单
     *
     * @param pageNo      当前页
     * @param pageSize    每页条数
     * @param wxCode      微信code
     * @param orderStatus 订单状态
     * @return
     */
    PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus);

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    BaseResult findByOrderId(Long orderId);

    /**
     * 退款
     *
     * @param orderId
     * @return
     */
    BaseResult refund(Long orderId);

    /**
     * 签收后评价
     *
     * @param estimate
     * @return
     */
    BaseResult estimate(Estimate estimate);

    /**
     * 确认收货
     *
     * @param id
     * @param ipAddress
     * @return
     */
    BaseResult confirmReceipt(Long id, String ipAddress);

    /**
     * 取消订单
     *
     * @param id
     * @param ipAddress
     * @return
     */
    BaseResult cancelOrder(Long id, String ipAddress);
}
