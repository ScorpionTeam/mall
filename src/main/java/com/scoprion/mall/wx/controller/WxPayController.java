package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.rabbitmq.SendComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.wx.service.pay.WxPayService;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@RestController
@RequestMapping("wx")
public class WxPayController {


    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private SendComponent sendComponent;

    /**
     * 预付款订单信息
     * 付款签名信息
     *
     * @param order
     * @param wxCode
     * @param request
     * @return
     */
    @Access
    @RequestMapping(value = "/jsapi/order/unifiedOrder", method = RequestMethod.GET)
    public BaseResult preOrder(String order, String wxCode, HttpServletRequest request) {
        WxOrderRequestData wxOrderRequestData = JSON.parseObject(order, WxOrderRequestData.class);
        wxOrderRequestData.setWxCode(wxCode);
        String ipAddress = IPUtil.getIPAddress(request);
        wxOrderRequestData.setIpAddress(ipAddress);
        sendComponent.send(wxOrderRequestData);
//        return null;
        return wxPayService.unifiedOrder(wxOrderRequestData);
    }


    /**
     * 重新发起支付
     *
     * @param orderId
     * @return
     */
    @Access
    @RequestMapping(value = "/jsapi/order/pay", method = RequestMethod.GET)
    public BaseResult pay(Long orderId) {
        return wxPayService.pay(orderId);
    }

    /**
     * 接收微信回调
     *
     * @param request
     * @return
     */
    @Access(need = false)
    @RequestMapping(value = "/jsapi/callback/pay", method = RequestMethod.POST)
    public void pay(HttpServletRequest request) {
        String inputLine;
        StringBuffer notifyXml = new StringBuffer();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml.append(inputLine);
            }
            request.getReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData = WxPayUtil.castXMLStringToUnifiedOrderNotifyRequestData(
                notifyXml.toString());

        //回调出现错误 返回
        if (unifiedOrderNotifyRequestData.getReturn_code().equalsIgnoreCase("FAIL")) {
            LOGGER.error("微信回调出错@------" + unifiedOrderNotifyRequestData.getReturn_msg());
            return;
        }

        BaseResult baseResult = wxPayService.callback(unifiedOrderNotifyRequestData);

        //回调成功   通知微信已经接收完成
        if (baseResult.getResult() == 1) {
            UnifiedOrderNotifyResponseData unifiedOrderNotifyResponseData = new UnifiedOrderNotifyResponseData();
            unifiedOrderNotifyResponseData.setReturn_code("SUCCESS");
            unifiedOrderNotifyResponseData.setReturn_msg("OK");
            String responseXML = WxPayUtil.castDataToXMLString(unifiedOrderNotifyResponseData);
            //通知微信回调接收成功
            WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", responseXML);
        }

    }


}
