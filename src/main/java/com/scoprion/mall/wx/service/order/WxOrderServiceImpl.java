package com.scoprion.mall.wx.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.SendGoodMapper;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    private WxOrderMapper wxOrderMapper;

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
        if (null == order) {
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
        if (estimate.getId() == null) {
            return BaseResult.notFound();
        }
        int result = wxOrderMapper.estimate(estimate);
        if (result > 0) {
            return BaseResult.success("评价成功");
        }
        return BaseResult.error("estimate_fail", "评价失败");
    }

    /**
     * 投诉
     *
     * @param id
     * @param complain
     * @return
     */
    @Override
    public BaseResult complain(Long id, String complain) {
        int result = wxOrderMapper.complain(id, complain);
        if (result > 0) {
            return BaseResult.success("投诉成功");
        }
        return BaseResult.error("complain_fail", "投诉失败");
    }


    /**
     * 确认收货
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult confirmReceipt(Long id, String wxCode) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(wxCode)) {
            return BaseResult.parameterError();
        }
        String userId = WxUtil.getOpenId(wxCode);
        Order order = wxOrderMapper.findByOrderId(id);
        if (!userId.equals(order.getUserId())) {
            BaseResult.error("confirm_fail", "未找到订单，确认收货失败");
        }
        if (!Constant.STATUS_THREE.equals(order.getOrderStatus())) {
            BaseResult.error("confirm_fail", "订单状态异常，不能确认收货");
        }
        int result = wxOrderMapper.updateByOrderID(id, "4");
        if (result > 0) {
            return BaseResult.success("确认收货成功");
        }
        return BaseResult.error("confirm_fail", "确认收货失败");
    }
}
