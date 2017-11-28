package com.scoprion.mall.wx.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.SendGoodMapper;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    @Autowired
    private SendGoodMapper sendGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    /**
     * 我的订单
     *
     * @param pageNo      当前页
     * @param pageSize    每页条数
     * @param wxCode      微信code
     * @param orderStatus 订单状态
     * @return
     */
    @Override
    public PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus) {
        //暂时使用直接传userId的方式 查询订单列表
        String userId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        if ("0".equals(orderStatus)) {
            orderStatus = null;
        }
        Page<OrderExt> page = wxOrderMapper.findByUserId(userId, orderStatus);
        return new PageResult(page);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResult findByOrderId(Long orderId) {
        Order order = wxOrderMapper.findByOrderId(orderId);
        if (StringUtils.isEmpty(order.toString())) {
            return BaseResult.notFound();
        }
        SendGood sendGood = sendGoodMapper.findById(order.getSendGoodId());
        OrderExt orderExt = new OrderExt();
        BeanUtils.copyProperties(order, orderExt);
        orderExt.setSendGood(sendGood);
        Delivery delivery = wxDeliveryMapper.findById(order.getDeliveryId());
        orderExt.setDelivery(delivery);
        return BaseResult.success(orderExt);
    }

    /**
     * 退款
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResult refund(Long orderId) {
        wxOrderMapper.updateByOrderID(orderId, "5");
        return BaseResult.success("申请成功");
    }


    /**
     * 签收后评价
     *
     * @param estimate
     * @return
     */
    @Override
    public BaseResult estimate(Estimate estimate) {
        String userId = WxUtil.getOpenId(estimate.getWxCode());
        if (StringUtils.isEmpty(userId)) {
            return BaseResult.notFound();
        }
        if (StringUtils.isEmpty(estimate.getGoodId().toString())) {
            return BaseResult.notFound();
        }
        estimate.setUserId(WxUtil.getOpenId(estimate.getUserId()));
        int result = wxOrderMapper.estimate(estimate);
        if (result > 0) {
            return BaseResult.success("评价成功");
        }
        return BaseResult.error("estimate_fail", "评价失败");
    }

    /**
     * 确认收货
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult confirmReceipt(Long id, String ipAddress) {
        if (StringUtils.isEmpty(id.toString())) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(id);
        if (!Constant.STATUS_THREE.equals(order.getOrderStatus())) {
            return BaseResult.error("confirm_fail", "订单状态异常，不能确认收货");
        }
        int result = wxOrderMapper.updateByOrderID(id, "4");
        if (result > 0) {
            saveOrderLog("确认收货", order, ipAddress);
            return BaseResult.success("确认收货成功");
        }
        return BaseResult.error("confirm_fail", "确认收货失败");
    }


    /**
     * 取消订单，进处于代付款状态的订单才能执行取消订单操作
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult cancelOrder(Long id, String ipAddress) {
        if (StringUtils.isEmpty(id.toString())) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(id);
        if (!Constant.STATUS_ONE.equals(order.getOrderStatus())) {
            return BaseResult.error("cancel_fail", "订单状态异常，不能取消订单");
        }
        int result = wxOrderMapper.updateByOrderID(id, "6");
        if (result > 0) {
            saveOrderLog("取消订单", order, ipAddress);
            return BaseResult.success("取消订单成功");
        }
        return BaseResult.error("cancel_fail", "取消订单失败");
    }

    private void saveOrderLog(String action, Order order, String ipAddress) {
        OrderLog orderLog = new OrderLog();
        orderLog.setAction(action);
        orderLog.setOrderId(order.getId());
        orderLog.setOrderNo(order.getOrderNo());
        orderLog.setIpAddress(ipAddress);
        wxOrderLogMapper.add(orderLog);
    }
}
