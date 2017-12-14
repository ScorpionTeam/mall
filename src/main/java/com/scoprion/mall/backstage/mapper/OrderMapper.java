package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderExt;
import com.scoprion.mall.domain.request.OrderRequestParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/29.
 *
 * @author ycj
 */
@Mapper
public interface OrderMapper {

    /**
     * 订单列表
     *
     * @param requestParams
     * @return
     */
    Page<OrderExt> findByCondition(OrderRequestParams requestParams);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    OrderExt findById(@Param("id") Long id);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    int modify(Order order);

    /**
     * 修改订单退款状态
     *
     * @param orderId
     * @param status
     * @param refundFee
     * @param remark
     * @return
     */
    int updateOrderRefundById(@Param("orderId") Long orderId,
                              @Param("status") String status,
                              @Param("refundFee") Integer refundFee,
                              @Param("remark") String remark);

    /**
     * 将订单支付状态
     *
     * @param orderId
     * @param orderStatus
     * @param wxOrderNo
     * @param orderNo
     * @param payDate
     * @return
     */
    int updateOrderPayStatus(@Param("orderId") Long orderId,
                             @Param("orderStatus") String orderStatus,
                             @Param("wxOrderNo") String wxOrderNo,
                             @Param("orderNo") String orderNo,
                             @Param("payDate") String payDate);

    /**
     * 修改订单发货状态
     *
     * @param id          订单id
     * @param orderStatus 订单状态
     * @param deliveryNo  订单号
     * @param sendGoodId  发货信息id
     * @return
     */
    int updateSendGood(@Param("id") Long id,
                       @Param("orderStatus") String orderStatus,
                       @Param("deliveryNo") String deliveryNo,
                       @Param("sendGoodId") Long sendGoodId);
}
