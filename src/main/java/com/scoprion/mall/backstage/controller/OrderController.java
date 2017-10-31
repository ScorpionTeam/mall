package com.scoprion.mall.backstage.controller;

import com.scoprion.annotation.AccessSecret;
import com.scoprion.mall.domain.Member;
import com.scoprion.mall.backstage.service.order.OrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.EncryptUtil;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 我的订单
     *
     * @param pageNo
     * @param pageSize
     * @param status   状态
     *                 0 全部
     *                 1 待付款
     *                 2 待发货
     *                 3 待收货
     *                 4 已完成
     * @param userId
     * @return
     */
    @AccessSecret
    @RequestMapping(value = "/my-order", method = RequestMethod.GET)
    public PageResult myOrder(int pageNo, int pageSize, Long userId, String status) {
        return orderService.myOrder(pageNo, pageSize, userId, status);
    }

    /**
     * 查询订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param status
     * @param request
     * @return
     */
    @AccessSecret
    @ResponseBody
    @RequestMapping(value = "/order-list", method = RequestMethod.GET)
    public PageResult findByPage(int pageNo, int pageSize, String status, HttpServletRequest request) throws Exception {
        String tokenStr = request.getHeader("oauth");
        String decryptStr = EncryptUtil.aesDecrypt(tokenStr, "ScorpionMall8888");
        Member member = (Member) redisTemplate.opsForValue().get("Login:" + decryptStr);
        return orderService.findByPage(pageNo, pageSize, status, member.getId());
    }

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order-confirm", method = RequestMethod.POST)
    public BaseResult orderConfirm(Long goodId, Long deliveryId, HttpServletRequest request) throws Exception {
        String ipAddress = IPUtil.getIPAddress(request);
        return orderService.orderConfirm(goodId, deliveryId, ipAddress);
    }

    /**
     * 小程序   下拉刷新查询我的订单列表
     *
     * @return
     */
    @RequestMapping(value = "pageOnPullDownRefresh", method = RequestMethod.GET)
    public PageResult pageOnPullDownRefresh() {
        String str = "";
        return null;
    }

    @RequestMapping(value = "/mock-list", method = RequestMethod.GET)
    public BaseResult mockList() {
        return orderService.mockList();
    }


}
