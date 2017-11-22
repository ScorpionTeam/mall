package com.scoprion.mall.wx.service.free;


import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.FreeMapper;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
     * @param orderExt
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult apply(OrderExt orderExt, String ipAddress) {
        //String openId = WxUtil.getOpenId(wxCode);
        ActivityGoods activityGoods = freeMapper.findByActivityGoodId(orderExt.getActivityGoodId());
        Long activityId = activityGoods.getActivityId();
        //查询是否参加过该活动
        int result = freeMapper.validByActivityId(activityId, orderExt.getWxCode());
        if (result > 0) {
            return BaseResult.error("apply_fail", "您已参加过该活动");
        }
        Date currentDate=new Date();
        //查询活动详情
        Activity activity = freeMapper.findById(activityId);
        if (0 == activity.getNum()) {
            return BaseResult.error("apply_fail", "活动人数已满");
        }else if(currentDate.after(activity.getEndDate())){
            return BaseResult.error("apply_fail","活动已过期");
        }

        //生成商品快照
        Long goodId = activityGoods.getGoodId();
        Goods goods = freeMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);

        //生成预付款订单
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        orderExt.setOrderNo(orderNo);
        orderExt.setUserId(orderExt.getWxCode());
        orderExt.setPayType("");
        orderExt.setOrderType("3");
        orderExt.setOrderStatus("1");
        orderExt.setGoodName(goods.getGoodName());
        orderExt.setGoodSnapShotId(goodSnapshot.getId());
        orderExt.setGoodId(goodId);
        orderExt.setGoodName(goods.getGoodName());
        orderExt.setGoodFee(goods.getPrice());
        orderExt.setDelivery(orderExt.getDelivery());
        int orderResult =freeMapper.add(orderExt);
        if (orderResult <= 0) {
            return BaseResult.error("order_fail", "下单失败");
        }

        //系统内生成订单信息
        OrderLog orderLog=constructOrderLog(order.getOrderNo(),"生成试用订单",ipAddress);
        wxOrderLogMapper.add(orderLog);
        return BaseResult.success("成功");
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
