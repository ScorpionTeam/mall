package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Mapper
public interface OrderMapper {

    /**
     * 根据订单状态  用户id   分页查询
     *
     * @param status
     * @param userId
     * @return
     */
    Page<Order> findByPage(@Param("status") String status, @Param("userId") Long userId);

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    int add(Order order);

    List<Order> mockList();

    /**
     * 我的订单
     *
     * @param userId
     * @param status
     * @return
     */
    Page<Order> myOrder(@Param("userId") Long userId, @Param("status") String status);


}
