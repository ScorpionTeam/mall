package com.scoprion.mall.wx.controller;



import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.rabbitmq.SendComponent;
import com.scoprion.mall.wx.rabbitmq.SendFreeComponent;
import com.scoprion.mall.wx.service.free.WxFreeService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 试用接口
 *
 * @author by hmy
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("wx/free")
public class WxFreeController {

    @Autowired
    private WxFreeService wxFreeService;

    @Autowired
    private SendFreeComponent sendFreeComponent;

    /**
     * 试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Access
    @RequestMapping(value = "/findFreeList", method = RequestMethod.GET)
    public PageResult findAll(Integer pageNo, Integer pageSize) {
        return wxFreeService.findAll(pageNo, pageSize);
    }

    /**
     * 参加试用
     *
     * @param wxFreeOrder 订单
     * @param request
     * @return
     */

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public BaseResult apply(@RequestBody WxFreeOrder wxFreeOrder,HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        wxFreeOrder.setIpAddress(ipAddress);
        sendFreeComponent.send(wxFreeOrder);
        return wxFreeService.apply(wxFreeOrder);
    }

    /**
     * 支付
     *
     * @param orderId
     * @param activityId
     * @param goodId
     * @return
     */
    @Access
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public BaseResult pay(Long orderId,Long activityId,Long goodId){
        return wxFreeService.pay(orderId,activityId,goodId);
    }

}
