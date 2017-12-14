package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Estimate;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */

@Mapper
public interface WxOrderMapper {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 根据订单号修改微信预付款订单号
     *
     * @param orderId
     * @param prepayId
     * @return
     */
    int updateOrderForPrepayId(@Param("orderId") Long orderId,
                               @Param("prepayId") String prepayId);

    /**
     * 根据订单号修改订单状态 以及付款状态 & 微信订单号
     *
     * @param orderNo
     * @param payDate
     * @param wxOrderNo
     * @return
     */
    int updateOrderStatusAndPayStatusAndWxOrderNo(@Param("payDate") String payDate,
                                                  @Param("orderNo") String orderNo,
                                                  @Param("wxOrderNo") String wxOrderNo);

    /**
     * 根据微信单号查询订单信息
     *
     * @param orderNo
     * @return
     */
    Order findByWxOrderNo(@Param("orderNo") String orderNo);

    /**
     * 我的订单
     *
     * @param userId
     * @param orderStatus
     * @return
     */
    Page<OrderExt> findByUserId(@Param("userId") String userId,
                                @Param("orderStatus") String orderStatus);

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    Order findByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据订单id修改订单状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    int updateByOrderID(@Param("orderId") Long orderId, @Param("orderStatus") String orderStatus);

    /**
     * 签收后评价
     *
     * @param estimate
     * @return
     */
    int estimate(Estimate estimate);

    /**
     * 根据orderId查询 预付款信息
     *
     * @param orderId
     * @return
     */

    String findPrepayIdByOrderId(@Param("orderId") Long orderId);

    /**
     * 查询 申请退款时间大于两天（48小时） 的订单
     *
     * @return
     */
    List<Order> findRefundingOrder();

    /**
     * 订单列表
     *
     * @return
     */
    List<Order> findUnPayOrder();


    /**
     * 更新订单状态
     *
     * @param id          订单id
     * @param orderStatus 状态
     * @return
     */
    Integer updateOrderStatusById(@Param("id") Long id,
                                  @Param("orderStatus") String orderStatus);


    /**
     * 更新订单状态
     * @param orderId
     * @return
     */
    int updateStatus(@Param("orderId") Long orderId);

    /**
     * 更改退款时间
     * @param orderId
     * @return
     */
    int update(@Param("orderId") Long orderId);
}
