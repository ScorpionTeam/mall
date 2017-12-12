package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.rabbitmq.SendComponent;
import com.scoprion.mall.wx.rabbitmq.SendFreeComponent;
import com.scoprion.mall.wx.service.free.WxFreeService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 试用接口
 *
 * @author by hmy
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("wx/free")
public class WxFreeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxFreeController.class);

    @Autowired
    private WxFreeService wxFreeService;

    @Autowired
    private SendFreeComponent sendFreeComponent;

    /**
     * 试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Access
    @RequestMapping(value = "/findFreeList", method = RequestMethod.GET)
    public PageResult findAll(Integer pageNo, Integer pageSize) {
        return wxFreeService.findAll(pageNo, pageSize);
    }

    /**
     * 参加试用
     *
     * @param wxFreeOrder 订单
     * @param request
     * @return
     */

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public BaseResult apply(@RequestBody WxFreeOrder wxFreeOrder,HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        wxFreeOrder.setIpAddress(ipAddress);
        sendFreeComponent.send(wxFreeOrder);
        return wxFreeService.apply(wxFreeOrder);
    }

    /**
     * 重新支付
     *
     * @param orderId

     * @return
     */
    @Access
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public BaseResult pay(Long orderId){
        return wxFreeService.pay(orderId);
    }

    /**
     * 接收微信回调(免费试用)
     *
     * @param request
     * @return
     */
    @Access(need = false)
    @RequestMapping(value = "/callBack", method = RequestMethod.POST)
    public void callBack(HttpServletRequest request) {
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

        BaseResult baseResult = wxFreeService.callBack(unifiedOrderNotifyRequestData);

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
