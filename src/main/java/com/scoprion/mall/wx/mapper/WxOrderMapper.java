package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.OrderLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Mapper
public interface WxOrderMapper {

    /**
     *订单列表
     * @param userId
     * @param orderStatus
     * @return
     */
    Page<Order> findByCondition (@Param("userId")Long userId,@Param("orderStatus")String orderStatus);

}
