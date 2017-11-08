package com.scoprion.mall.wx.controller;

import com.scoprion.mall.wx.service.order.WxOrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Administrator
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
    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public BaseResult findByOrderId(Long orderId) {
        return wxOrderService.findByOrderId(orderId);
    }


    /**
     * 退款申请
     *
     * @return
     */
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public BaseResult refund(Long orderId) {
        return wxOrderService.refund(orderId);
    }
}
