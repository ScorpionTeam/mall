package com.scoprion.mall.wx.controller;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@RestController
@RequestMapping("wx/orders")
public class WxOrderController {


    /**
     * 查询我的订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param orderStatus
     * @return
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public PageResult findByUserId(int pageNo, int pageSize, String orderStatus) {

        return null;
    }


    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public BaseResult findByOrderId(Long orderId) {
        return null;
    }

}
