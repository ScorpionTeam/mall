package com.scoprion.mall.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.Estimate;
import com.scoprion.mall.wx.service.order.WxOrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */

@RestController
@RequestMapping("wx/order")
public class WxOrderController {

    @Autowired
    private WxOrderService wxOrderService;

    /**
     * 查询我的订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @param orderStatus
     * @return
     */
    @Access
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus) {
        return wxOrderService.findByUserId(pageNo, pageSize, wxCode, orderStatus);
    }


    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Access
    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public BaseResult findByOrderId(Long orderId) {
        return wxOrderService.findByOrderId(orderId);
    }


    /**
     * 退款申请
     *
     * @return
     */
    @Access(need = false)
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public BaseResult refund(@RequestBody JSONObject jsonObject) {
        return wxOrderService.refund(jsonObject.getLong("orderId"));
    }

    /**
     * 签收后评价
     *
     * @param estimate
     * @return
     */
    @Access
    @RequestMapping(value = "/estimate", method = RequestMethod.POST)
    public BaseResult estimate(@RequestBody Estimate estimate) {
        return wxOrderService.estimate(estimate);
    }

    /**
     * 确认收货
     *
     * @param id
     * @return
     */
    @Access
    @RequestMapping(value = "/confirmReceipt", method = RequestMethod.GET)
    public BaseResult confirmReceipt(Long id, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return wxOrderService.confirmReceipt(id, ipAddress);
    }

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    @Access
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    public BaseResult cancelOrder(Long id, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return wxOrderService.cancelOrder(id, ipAddress);
    }

}
