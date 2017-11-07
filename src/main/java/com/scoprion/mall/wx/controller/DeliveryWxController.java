package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.wx.service.delivery.DeliveryWxService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin1 on 2017/11/1.
 */
@RestController
@RequestMapping("wx/delivery")
public class DeliveryWxController {
    @Autowired
    private DeliveryWxService deliveryWxService;

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
        return deliveryWxService.DeliveryList(userId,pageNo,pageSize);
    }

    /**
     * 新增收货地址
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addDelivery(@RequestBody Delivery delivery){

        return deliveryWxService.addDelivery(delivery);
    }


    /**
     * 修改收货地址
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateDelivery(@RequestBody Delivery delivery){
        return  deliveryWxService.updateDelivery(delivery);
    }


    /**
     * 删除收获地址
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteDelivery(Long id){
        return  deliveryWxService.deleteDelivery(id);
    }
}
