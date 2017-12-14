package com.scoprion.mall.wx.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.SendGoodMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderExt;
import com.scoprion.mall.domain.order.OrderLog;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
        String userId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        //所有订单
        if (CommonEnum.ALL.getCode().equals(orderStatus)) {
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
    @Transactional(rollbackFor = Exception.class)
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
        if (StringUtils.isEmpty(orderId.toString())) {
            return BaseResult.parameterError();
        }
//        wxOrderMapper.updateByOrderID(orderId, CommonEnum.REFUND.getCode());
        wxOrderMapper.updateStatus(orderId);
        wxOrderMapper.update(orderId);
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
        return BaseResult.error("ERROR", "评价失败");
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
        //待收货状态
        if (!CommonEnum.UN_RECEIVE.getCode().equals(order.getOrderStatus())) {
            return BaseResult.error("ERROR", "订单状态异常，不能确认收货");
        }
        //签收成功后订单状态修改为  待评价
        int result = wxOrderMapper.updateByOrderID(id, CommonEnum.DONE.getCode());
        if (result > 0) {
            ServiceCommon.saveWxOrderLog(order.getId(), null, order.getOrderNo(), "确认收货",
                    wxOrderLogMapper);
            return BaseResult.success("确认收货成功");
        }
        return BaseResult.error("ERROR", "确认收货失败");
    }


    /**
     * 取消订单，进处于代付款状态的订单才能执行取消订单操作
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult cancelOrder(Long id, String ipAddress) {
        if (StringUtils.isEmpty(id.toString())) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(id);
        //待付款状态
        if (!CommonEnum.UN_PAY.getCode().equals(order.getOrderStatus())) {
            return BaseResult.error("ERROR", "订单状态异常，不能取消订单");
        }
        //将订单修改为关闭状态
        int result = wxOrderMapper.updateByOrderID(id, CommonEnum.CLOSING.getCode());
        if (result > 0) {
            ServiceCommon.saveWxOrderLog(order.getId(), null, order.getOrderNo(), "取消订单",
                    wxOrderLogMapper);
            return BaseResult.success("取消订单成功");
        }
        return BaseResult.error("ERROR", "取消订单失败");
    }
}
