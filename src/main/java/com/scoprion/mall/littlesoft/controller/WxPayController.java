package com.scoprion.mall.littlesoft.controller;

import com.alibaba.fastjson.JSON;
import com.scoprion.mall.backstage.service.good.GoodService;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.littlesoft.service.pay.WxPayService;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import com.scoprion.wxpay.WxPayUtil;
import com.scoprion.wxpay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.wxpay.domain.UnifiedOrderNotifyResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@RestController
@RequestMapping("wx")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private GoodService goodService;

    /**
     * 生成预付款订单并付款
     *
     * @param order
     * @param wxCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/jsapi/order/pre-order", method = RequestMethod.GET)
    public BaseResult preOrder(String order, String wxCode, HttpServletRequest request) {
        WxOrderRequestData wxOrderRequestData = JSON.parseObject(order, WxOrderRequestData.class);
        String ipAddress = IPUtil.getIPAddress(request);
        return wxPayService.preOrder(wxOrderRequestData, wxCode, ipAddress);
    }


    /**
     * 去支付
     *
     * @param wxCode
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/jsapi/order/pay", method = RequestMethod.GET)
    public BaseResult pay(String wxCode, Long orderId) {
        return wxPayService.pay(wxCode, orderId);
    }

    /**
     * 接收微信回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/jsapi/callback/pay", method = RequestMethod.POST)
    public BaseResult pay(HttpServletRequest request) {
        String inputLine;
        String notifyXml = "";
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml += inputLine;
            }
            request.getReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UnifiedOrderNotifyRequestData unifiedOrderNotifyResponseData = WxPayUtil.castXMLStringToUnifiedOrderNotifyRequestData(notifyXml);
        System.out.println(notifyXml);
        return null;
    }


}
