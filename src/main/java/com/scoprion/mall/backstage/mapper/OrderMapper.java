package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Order;
import com.scoprion.result.BaseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param payType     支付类型0 支付宝1 微信2 信用卡3 储蓄卡
     * @param orderType   订单类型 1pc订单  2手机订单
     * @param orderStatus 状态  0 全部  1 待付款   2 待发货  3 待收货 4 已完成
     * @param searchKey   模糊查询信息
     * @param startDate   开始时间
     * @param endDate     结束时间
     * @param phone      手机号
     * @return
     */
    Page<Order> listPage(@Param("payType") String payType,
                         @Param("orderType") String orderType,
                         @Param("orderStatus") String orderStatus,
                         @Param("searchKey") String searchKey,
                         @Param("startDate") String startDate,
                         @Param("endDate") String endDate,
                         @Param("phone") String phone);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    Order findById(@Param("id") Long id);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    int modify(Order order);
}
