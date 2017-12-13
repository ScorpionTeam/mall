package com.scoprion.mall.seller.service.order;

import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.request.OrderRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:27
 */
public interface SellerOrderService {
    /**
     * 订单列表
     *
     * @param requestParams
     * @return
     */
    PageResult findByCondition(OrderRequestParams requestParams);

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

    /**
     * 退款
     *
     * @param orderId
     * @param flag
     * @param remark
     * @param refundFee
     * @return
     */
    BaseResult refund(Long orderId, String flag, String remark, int refundFee);


    /**
     * 退货
     *
     * @param orderId 订单id
     * @param count   退货数量
     * @return
     */
    BaseResult goodReject(Long orderId, Integer count);

    /**
     * 商品发货
     *
     * @param orderId     订单号
     * @param deliveryNo  运单号
     * @param expressName 快递公司
     * @param expressNo   快递公司编号
     * @param senderId    寄件人Id
     * @return
     */
    BaseResult sendGood(Long orderId, String deliveryNo, String expressName,
                        String expressNo, Long senderId);

    /**
     * 根据订单id查订单日志
     *
     * @param pageNo
     * @param pageSize
     * @param orderId
     * @return
     */
    PageResult findOrderLogByOrderId(Integer pageNo, Integer pageSize, Long orderId);
}
