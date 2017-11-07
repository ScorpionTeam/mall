package com.scoprion.mall.wx.mapper;

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
     * 创建订单
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 根据订单号修改微信返回订单号
     *
     * @param orderId
     * @param wxOrderNo
     * @return
     */
    int updateOrderForWxOrderNo(Long orderId, String wxOrderNo);

    /**
     * 根据openid查询微信预付款订单号
     *
     * @param openId
     * @param orderId
     * @return
     */
    String findPrepayIdByOpenid(@Param("openId") String openId, @Param("orderId") Long orderId);

}
