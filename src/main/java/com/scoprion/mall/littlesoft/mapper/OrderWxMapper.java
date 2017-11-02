package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
public interface OrderWxMapper {

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param status
     * @return
     */
    Page<Order> getOrderList(@Param("userId") Long userId,@Param("status") String status);

    /**
     * 获取商品快照信息
     *
     * @param id
     * @return
     */
    GoodSnapshot getGoodSnapShot(@Param("id") Long id);
}
