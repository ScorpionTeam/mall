package com.scoprion.mall.littlesoft.mapper;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.OrderExt;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
@Mapper
public interface OrderLogWxMapper {

    /**
     * 创建订单日志
     * @param orderExt
     */
    int add(OrderExt orderExt);
}
