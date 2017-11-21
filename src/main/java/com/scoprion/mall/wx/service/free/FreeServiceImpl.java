package com.scoprion.mall.wx.service.free;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.FreeMapper;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
@Service
public class FreeServiceImpl implements FreeService {

    @Autowired
    private FreeMapper freeMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAll(int pageNo, int pageSize) {
        return null;
    }

    /**
     * 参加试用活动
     *
     * @param activityGoodId
     * @param wxCode
     * @return
     */
    @Override
    public BaseResult apply(Long activityGoodId, String wxCode) {
        String openId = WxUtil.getOpenId(wxCode);
        ActivityGoods activityGoods = freeMapper.findByActivityGoodId(activityGoodId);
        Long activityId = activityGoods.getActivityId();
        //查询是否参加过该活动
        int result = freeMapper.validByActivityId(activityId, openId);
        if (result > 0) {
            return BaseResult.error("apply_fail", "您已参加过该活动");
        }

        int result1 = freeMapper.validByActivityIdAndDate(activityId);
        if (result1 <= 0) {
            return BaseResult.error("apply_fail", "活动已过期");
        }
        //查询活动详情
        Activity activity = freeMapper.findById(activityId);
        if (0 == activity.getNum()) {
            return BaseResult.error("apply_fail", "活动人数已满");
        }

        //生成商品快照
        Long goodId=activityGoods.getGoodId();
        Goods goods=freeMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods,goodSnapshot);

        //生成预付款订单
        Order order=new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(openId);
        order.setPayType("");
        order.setOrderType("2");
        order.setOrderStatus("1");
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setGoodId(goodId);
        order.setGoodName(goods.getGoodName());
        order.setGoodFee(goods.getPrice());
        int orderResult=wxOrderMapper.add(order);
        if (orderResult<=0){
            return BaseResult.error("order_fail","下单失败");
        }

        //系统内生成订单信息
//        OrderLog orderLog=constructOrderLog(order.getOrderNo(),"生成预付款订单",ipAddress);
//        wxOrderLogMapper.add(orderLog);
        return null;
    }

    /**
     * 订单日志构造
     *
     * @param orderNo   订单no
     * @param action    动作
     * @param ipAddress IP地址
     * @return
     */
    private OrderLog constructOrderLog(String orderNo, String action, String ipAddress) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNo(orderNo);
        orderLog.setAction(action);
        orderLog.setIpAddress(ipAddress);
        return orderLog;
    }
}
