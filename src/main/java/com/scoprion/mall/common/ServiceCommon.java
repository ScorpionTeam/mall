package com.scoprion.mall.common;

import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.backstage.mapper.OrderLogMapper;
import com.scoprion.mall.domain.good.GoodLog;
import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.UserActivity;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.domain.order.OrderLog;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 业务公用
 *
 * @author by kunlun
 * @created on 2017/11/29.
 */
public class ServiceCommon {

    /**
     * 商品快照组装
     *
     * @param goods
     * @param goodId
     * @return
     */
    public static GoodSnapshot snapshotConstructor(Goods goods, Long goodId) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goodId);
        goodSnapshot.setGoodDescription(goods.getDescription());
        return goodSnapshot;
    }


    /**
     * 组装参加活动记录
     *
     * @param wxFreeOrder
     * @param userId
     * @return
     */
    public static UserActivity userActivityConstructor(WxFreeOrder wxFreeOrder, String userId) {
        UserActivity userActivity = new UserActivity();
        userActivity.setActivityId(wxFreeOrder.getActivityId());
        userActivity.setGoodId(wxFreeOrder.getActivityId());
        userActivity.setUserId(userId);
        return userActivity;
    }


    /**
     * @param orderId
     * @param ipAddress
     * @param orderNo
     * @param action
     * @param orderLogMapper
     */
    public static void saveOrderLog(Long orderId,
                                    String ipAddress,
                                    String orderNo,
                                    String action,
                                    OrderLogMapper orderLogMapper) {
        OrderLog orderLog = new OrderLog();
        orderLog.setAction(action);
        orderLog.setIpAddress(ipAddress);
        orderLog.setOrderId(orderId);
        orderLog.setOrderNo(orderNo);
        orderLogMapper.add(orderLog);
    }

    /**
     * 保存订单日志
     *
     * @param orderId
     * @param ipAddress
     * @param orderNo
     * @param action
     * @param wxOrderLogMapper
     */
    public static void saveWxOrderLog(Long orderId,
                                      String ipAddress,
                                      String orderNo,
                                      String action,
                                      WxOrderLogMapper wxOrderLogMapper) {
        OrderLog orderLog = new OrderLog();
        orderLog.setIpAddress(ipAddress);
        orderLog.setAction(action);
        orderLog.setOrderNo(orderNo);
        orderLog.setOrderId(orderId);
        wxOrderLogMapper.add(orderLog);
    }

    public static void saveGoodLog(String gName,
                                   String action,
                                   Long goodId,
                                   GoodLogMapper goodLogMapper) {
        GoodLog goodLog = new GoodLog();
        goodLog.setAction(action);
        goodLog.setGoodId(goodId);
        goodLog.setGoodName(gName);
        goodLogMapper.add(goodLog);
    }

}
