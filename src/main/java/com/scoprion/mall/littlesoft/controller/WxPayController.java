package com.scoprion.mall.littlesoft.controller;

import com.alibaba.fastjson.JSON;
import com.scoprion.mall.backstage.service.good.GoodService;
import com.scoprion.mall.domain.OrderExt;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import com.scoprion.utils.UUIDHexGenerator;
import com.scoprion.wxpay.AuthorizationCode;
import com.scoprion.wxpay.WxPayConfig;
import com.scoprion.wxpay.WxPayUtil;
import com.scoprion.wxpay.WxUtil;
import com.scoprion.wxpay.domain.UnifiedOrderResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@RestController
@RequestMapping("wx")
public class WxPayController {


    @Autowired
    private GoodService goodService;

    /**
     * 根据code 获取用户openid
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/jsapi/get/openid", method = RequestMethod.GET)
    public BaseResult openid(String code) {
        String apiUrl = WxPayConfig.OPEN_ID_URL
                + "appid=" + WxPayConfig.APPID
                + "&secret=" + WxPayConfig.APPSECRET +
                "&js_code=" + code
                + "&grant_type=authorization_code";
        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        AuthorizationCode authorizationCode = JSON.parseObject(response, AuthorizationCode.class);
        return BaseResult.success(authorizationCode);
    }

    /**
     * @param code      用户 code
     * @param body      商品描述
     * @param total_fee 商品价格
     * @param detail    商品详情
     * @param request   request请求信息
     * @return
     */
    @RequestMapping(value = "/jsapi/pre-order", method = RequestMethod.GET)
    public BaseResult preparaOrder(String code, String body, int total_fee, String detail, HttpServletRequest request) {

        //获取openid
        String apiUrl = WxPayConfig.OPEN_ID_URL
                + "appid=" + WxPayConfig.APPID
                + "&secret=" + WxPayConfig.APPSECRET +
                "&js_code=" + code
                + "&grant_type=authorization_code";
        String response = WxUtil.httpsRequest(apiUrl, "GET", null);

        AuthorizationCode authorizationCode = JSON.parseObject(response, AuthorizationCode.class);
        Map<String, Object> map = new HashMap<>();
        String appid = WxPayConfig.APPID;
        String mch_id = WxPayConfig.MCHID;
        String device_info = "WEB";
        String nonce_str = WxUtil.createRandom(true, 10);
        String attach = "妆口袋";
        map.put("appid", appid);
        map.put("openid", authorizationCode.getOpenid());
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("device_info", "100000");
        map.put("nonce_str", WxUtil.createRandom(true, 10));
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        UUIDHexGenerator uuid = UUIDHexGenerator.getInstance();
        map.put("out_trade_no", uuid.generate());
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", IPUtil.getIPAddress(request));
        map.put("notify_url", WxPayConfig.NOTIFY_URL);
        map.put("trade_type", "JSAPI");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        String xmlString = WxPayUtil.MapConvertToXML(map);
        System.out.println("要发送的数据" + xmlString);
        String responseStr = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "GET", xmlString);
        UnifiedOrderResponseData unifiedOrderResponseData = WxPayUtil.castXMLStringToUnifiedOrderResponseData(
                responseStr);
        System.out.println("返回结果" + responseStr);
        Long timeStamp = System.currentTimeMillis() / 1000;
        Map<String, Object> signMap = new HashMap<>();
        signMap.put("appId", WxPayConfig.APPID);
        String nonceStr = WxUtil.createRandom(false, 10);
        signMap.put("nonceStr", nonceStr);
        signMap.put("package", "prepay_id=" + unifiedOrderResponseData.getPrepay_id());
        signMap.put("signType", "MD5");
        signMap.put("timeStamp", timeStamp);
        System.out.println("最终签名体:" + WxPayUtil.sort(signMap));
        String paySign = WxUtil.MD5(WxPayUtil.sort(signMap)).toUpperCase();
        System.out.println("签名:" + paySign);
        unifiedOrderResponseData.setPaySign(paySign);
        unifiedOrderResponseData.setTimeStamp(timeStamp.toString());
        unifiedOrderResponseData.setNonce_str(nonceStr);
        return BaseResult.success(unifiedOrderResponseData);

    }

    /**
     * 接收微信回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/jsapi/callback/pay", method = RequestMethod.POST)
    public BaseResult pay(HttpServletRequest request) {

        System.out.println("进入回调");
        return null;
    }

    @RequestMapping(value = "/jsapi/order/pre-order",method = RequestMethod.GET)
    public BaseResult preOrder(@RequestBody OrderExt orderExt){

        return null;
    }

}
