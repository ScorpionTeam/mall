package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.wx.service.delivery.WxDeliveryService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin1 on 2017/11/1.
 */
@RestController
@RequestMapping("wx/delivery")
public class WxDeliveryController {
    @Autowired
    private WxDeliveryService wxDeliveryService;


    /**
     * 分页查询收货地址
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public PageResult findByUserId(int pageNo, int pageSize, String userId) {
        return wxDeliveryService.findByUserId(pageNo, pageSize, userId);
    }

    /**
     * 新增收货地址
     *
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addDelivery(@RequestBody Delivery delivery) {

        return wxDeliveryService.add(delivery);
    }


    /**
     * 修改收货地址
     *
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateDelivery(@RequestBody Delivery delivery) {
        return wxDeliveryService.updateDelivery(delivery);
    }


    /**
     * 删除收获地址
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteDelivery(Long id) {
        return wxDeliveryService.deleteDelivery(id);
    }
}
