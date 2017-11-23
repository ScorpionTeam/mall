package com.scoprion.mall.wx.controller;


import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.DeliveryExt;
import com.scoprion.mall.domain.OrderExt;
import com.scoprion.mall.wx.service.free.FreeService;
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
 * 试用接口
 *
 * @author by kunlun
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("wx/free")
public class FreeController {

    @Autowired
    private FreeService freeService;

    /**
     * 试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResult findAll(int pageNo, int pageSize) {
        return freeService.findAll(pageNo, pageSize);
    }

    /**
     * 参加试用
     *
     * @param orderExt 订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public BaseResult apply(@RequestBody OrderExt orderExt, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return freeService.apply(orderExt, ipAddress);
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public BaseResult pay(String wxCode,Long orderId){
        return null;
    }
}
