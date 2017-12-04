package com.scoprion.mall.wx.controller;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.DeliveryExt;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.service.delivery.WxDeliveryService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by hmy
 * @created on 2017/11/1.
 */
@RestController
@RequestMapping("wx/delivery")
public class WxDeliveryController {
    @Autowired
    private WxDeliveryService wxDeliveryService;

    /**
     * 查询用户收货地址列表
     *
     * @param wxCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findByWxCode", method = RequestMethod.GET)
    public PageResult findByWxCode(String wxCode, Integer pageNo, Integer pageSize) {
        return wxDeliveryService.findByWxCode(wxCode, pageNo, pageSize);
    }

    /**
     * 新增收货地址
     *
     * @param delivery
     * @return
     */
    @Access
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody DeliveryExt delivery) {
        if (StringUtils.isEmpty(delivery.getWxCode())) {
            return BaseResult.parameterError();
        }
        String openId = WxUtil.getOpenId(delivery.getWxCode());
        delivery.setUserId(openId);
        return wxDeliveryService.add(delivery);
    }


    /**
     * 修改收货地址
     *
     * @param delivery
     * @return
     */
    @Access
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult updateDelivery(@RequestBody Delivery delivery) {
        return wxDeliveryService.updateDelivery(delivery);
    }


    /**
     * 删除收货地址
     *
     * @param deliveryExt
     * @return
     */
    @Access
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult deleteDelivery(@RequestBody DeliveryExt deliveryExt) {
        return wxDeliveryService.deleteDelivery(deliveryExt);
    }


    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    @Access
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        if (StringUtils.isEmpty(id.toString())) {
            return BaseResult.parameterError();
        }
        return wxDeliveryService.findById(id);
    }

    /**
     * 设置默认收货地址
     *
     * @param id
     * @return
     */
    @Access
    @RequestMapping(value = "/defaultAddress", method = RequestMethod.GET)
    public BaseResult defaultAddress(Long id, String wxCode) {
        return wxDeliveryService.defaultAddress(id, wxCode);
    }

    /**
     * 获取默认地址
     *
     * @param wxCode
     * @return
     */
    @Access
    @RequestMapping(value = "/getDefault", method = RequestMethod.GET)
    public BaseResult getDefault(String wxCode) {
        return wxDeliveryService.getDefault(wxCode);
    }
}
