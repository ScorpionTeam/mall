package com.scoprion.mall.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/29.
 */
@Mapper
public interface OrderMapper {

    /**
     * 根据订单状态  用户id   分页查询
     * @param status
     * @param userId
     * @return
     */
    Page<Order> findByPage(@Param("status") String status, @Param("userId") Long userId);

    /**
     * 创建订单
     * @param order
     * @return
     */
    int add(Order order);


}
