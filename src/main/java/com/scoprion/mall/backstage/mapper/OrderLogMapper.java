package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.OrderLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@Mapper
public interface OrderLogMapper {
    /**
     * 添加日志
     *
     * @param orderLog
     */
    void add(OrderLog orderLog);

    /**
     * 根据订单id查询订单日志
     *
     * @param orderId
     * @return
     */
    Page<OrderLog> findByOrderId(@Param("orderId") Long orderId);
}
