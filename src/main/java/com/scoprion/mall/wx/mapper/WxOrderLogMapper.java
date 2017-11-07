package com.scoprion.mall.littlesoft.mapper;

import com.scoprion.mall.domain.OrderLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Mapper
public interface WxOrderLogMapper {


    /**
     * 根据id获取订单日志
     * @param orderId
     * @return
     */
    OrderLog findOrderLog(@Param("orderId")Long orderId);
}
