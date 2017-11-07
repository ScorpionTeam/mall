package com.scoprion.mall.wx.pay;

import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scoprion.mall.backstage.service.good.GoodService;
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
public class PayController {

    @Autowired
    private WxPayService wxPayService;

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
        UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData = WxPayUtil.castXMLStringToUnifiedOrderNotifyRequestData(
                notifyXml);
        BaseResult baseResult = wxPayService.callback(unifiedOrderNotifyRequestData);
        if (baseResult.getResult() == 1) {
            UnifiedOrderNotifyResponseData unifiedOrderNotifyResponseData = new UnifiedOrderNotifyResponseData();
            unifiedOrderNotifyResponseData.setReturn_code("SUCCESS");
            unifiedOrderNotifyResponseData.setReturn_msg("OK");
            String responseXML = WxPayUtil.castDataToXMLString(unifiedOrderNotifyResponseData);
            //通知微信回调接收成功
            WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", responseXML);
            return BaseResult.success("付款成功");
        } else {
            return baseResult;
        }

    }


}
