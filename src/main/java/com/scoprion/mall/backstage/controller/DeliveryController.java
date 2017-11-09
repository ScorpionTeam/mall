package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.backstage.service.delivery.DeliveryService;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author  by huangwenshuai
 * @created on 2017/10/13.
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
    public BaseResult add(@RequestBody Delivery delivery) {return deliveryService.add(delivery);
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

    /**
     * 收货地址编辑
     *
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public BaseResult edit(@RequestBody Delivery delivery) {
        return deliveryService.edit(delivery);
    }

}
