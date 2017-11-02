package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.delivery.DeliveryLittleSoftService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin1 on 2017/11/1.
 */
@RestController
@RequestMapping("wx/delivery")
public class DeliveryLittleSoftController {
    @Autowired
    private DeliveryLittleSoftService deliveryService;

    /**
     * 查询用户收获地址列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public PageResult DeliveryList(Long userId, Integer pageNo, Integer pageSize){
        return deliveryService.DeliveryList(userId,pageNo,pageSize);
    }

}
