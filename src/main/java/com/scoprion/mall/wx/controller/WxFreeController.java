package com.scoprion.mall.wx.controller;


import com.scoprion.mall.domain.OrderExt;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.service.free.WxFreeService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
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
 * @author by kunlun
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("wx/free")
public class WxFreeController {

    @Autowired
    private WxFreeService wxFreeService;

    /**
     * 试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
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
    public BaseResult apply(@RequestBody WxFreeOrder wxFreeOrder) {
//        String ipAddress = IPUtil.getIPAddress(request);
        return wxFreeService.apply(wxFreeOrder, null);
    }

    /**
     * 支付
     * @param wxCode
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public BaseResult pay(String wxCode,Long orderId){
        return wxFreeService.pay(wxCode,orderId);
    }

    /**
     * 接收微信回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public BaseResult pay(HttpServletRequest request) {
        String notifyXml = "";
        String inputLine;
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
        BaseResult baseResult = wxFreeService.callback(unifiedOrderNotifyRequestData);
        if (baseResult.getResult() == 1) {
            UnifiedOrderNotifyResponseData unifiedOrderNotifyResponseData = new UnifiedOrderNotifyResponseData();
            unifiedOrderNotifyResponseData.setReturn_msg("OK");
            unifiedOrderNotifyResponseData.setReturn_code("SUCCESS");
            String responseXML = WxPayUtil.castDataToXMLString(unifiedOrderNotifyResponseData);
            //通知微信回调接收成功
            WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", responseXML);
            return BaseResult.success("付款成功");
        } else {
            return baseResult;
        }

    }
}
