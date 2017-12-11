package com.scoprion.mall.seller.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderExt;
import com.scoprion.mall.domain.request.OrderRequestParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:26
 */
@Mapper
public interface SellerOrderMapper {

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    OrderExt findById(@Param("id") Long id);

    /**
     * 订单列表
     *
     * @param requestParams
     * @return
     */
    Page<OrderExt> findByCondition(OrderRequestParams requestParams);


    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    int modify(Order order);

    /**
     * 将订单状态修改为  拒绝退款
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
