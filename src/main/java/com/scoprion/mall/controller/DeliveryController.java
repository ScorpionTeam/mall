package com.scoprion.mall.controller;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.service.delivery.DeliveryService;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/10/13.
 */
@Controller
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;


    /**
     * 创建收货地址
     *
     * @param delivery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Delivery delivery) {
        return deliveryService.add(delivery);
    }

    /**
     * 根据用户id查询收货地址列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delivery-all", method = RequestMethod.GET)
    public BaseResult deliveryAll(Long userId) {
        return BaseResult.success(deliveryService.findAllByUserId(userId));
    }

}
