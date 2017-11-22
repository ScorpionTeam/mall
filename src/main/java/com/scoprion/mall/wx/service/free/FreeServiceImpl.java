package com.scoprion.mall.wx.service.free;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.FreeMapper;
import com.scoprion.mall.wx.mapper.WxActivityMapper;
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

    @Autowired
    private WxActivityMapper wxActivityMapper;

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Activity> page = wxActivityMapper.findAll();
        return new PageResult(page);
    }

    /**
     * 参加试用活动
     *
     * @param orderExt  订单
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult apply(OrderExt orderExt, String ipAddress) {
        //String openId = WxUtil.getOpenId(wxCode);
        //获得活动商品详情
        ActivityGoods activityGoods = freeMapper.findByActivityGoodId(orderExt.getActivityGoodId());
        Long activityId = activityGoods.getActivityId();
        //查询是否参加过该活动
        int result = freeMapper.validByActivityId(activityId, orderExt.getWxCode());
        if (result > 0) {
            return BaseResult.error("apply_fail", "您已参加过该活动");
        }
        Date currentDate = new Date();
        //查询活动详情
        Activity activity = freeMapper.findById(activityId);
        if (0 == activity.getNum()) {
            return BaseResult.error("apply_fail", "活动人数已满");
        } else if (currentDate.after(activity.getEndDate())) {
            return BaseResult.error("apply_fail", "活动已过期");
        }

        //生成商品快照
        Long goodId = activityGoods.getGoodId();
        Goods goods = freeMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);

        //生成预付款订单
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(orderExt.getWxCode());
        order.setPayType("");
        order.setOrderType("3");
        order.setOrderStatus("1");
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setGoodId(goodId);
        order.setGoodName(goods.getGoodName());
        order.setGoodFee(goods.getPrice());
        order.setDeliveryId(orderExt.getDelivery().getId());
        order.setAddress(orderExt.getDelivery().getAddress());
        order.setRecipients(orderExt.getDelivery().getRecipients());
        order.setPhone(orderExt.getDelivery().getPhone());
        order.setProvince(orderExt.getDelivery().getProvince());
        order.setCity(orderExt.getDelivery().getCity());
        order.setArea(orderExt.getDelivery().getArea());
        order.setPostCode(orderExt.getDelivery().getPostCode());
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("order_fail", "下单失败");
        }

        //系统内生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成试用订单", ipAddress);
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
