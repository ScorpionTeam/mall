package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.OrderExt;
import com.scoprion.mall.wx.service.order.OrderWxService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@RestController
@RequestMapping("wx/order")
public class OrderWxController {
    @Autowired
    private OrderWxService orderWxService;

    /**
     * 分页获取我的订单列表
     *
     * @param userId
     * @param status   0 全部
     *                 1 待付款
     *                 2 待发货
     *                 3 待收货
     *                 4 已完成
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/my-order", method = RequestMethod.GET)
    public PageResult getMyOrderList(Long userId, String status, Integer pageNo, Integer pageSize) {
        return orderWxService.getMyOrder(userId, status, pageNo, pageSize);
    }


    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @param request:用于获取客户端发起请求的请求头中的信息
     * @param purchase:订单量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ordered",method = RequestMethod.POST)
    public BaseResult ordered(Long goodId, Long deliveryId, HttpServletRequest request,Integer purchase) throws Exception {
        String ipAdress = IPUtil.getIPAddress(request);
        return  orderWxService.ordered(goodId,deliveryId,ipAdress,purchase);
    }
}
