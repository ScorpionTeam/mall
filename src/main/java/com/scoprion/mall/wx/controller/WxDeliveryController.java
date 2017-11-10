package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.DeliveryExt;
import com.scoprion.mall.wx.pay.util.WxUtil;
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
     * 查询用户收获地址列表
     *
     * @param wxCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public PageResult listPage(String wxCode, Integer pageNo, Integer pageSize){
        return wxDeliveryService.listPage(wxCode,pageNo,pageSize);
    }

    /**
     * 新增收货地址
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult add(@RequestBody DeliveryExt delivery){
        String openId = WxUtil.getOpenId(delivery.getWxCode());
        delivery.setUserId(openId);
        return wxDeliveryService.add(delivery);
    }


    /**
     * 修改收货地址
     * @param delivery
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseResult updateDelivery(@RequestBody Delivery delivery){
        return  wxDeliveryService.updateDelivery(delivery);
    }


    /**
     * 删除收获地址
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult deleteDelivery(Long id){
        return  wxDeliveryService.deleteDelivery(id);
    }


    /**
     * 获取收货地址详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public BaseResult findById(Long id){
        return wxDeliveryService.findById(id);
    }
}
