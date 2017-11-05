package com.scoprion.mall.littlesoft.controller;

import com.alibaba.fastjson.JSON;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import com.scoprion.utils.UUIDHexGenerator;
import com.scoprion.wxpay.OpenId;
import com.scoprion.wxpay.WxPayConfig;
import com.scoprion.wxpay.WxPayUtil;
import com.scoprion.wxpay.WxUtil;
import com.scoprion.wxpay.domain.UnifiedOrderResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@RestController
@RequestMapping("wx")
public class WxPayController {


    /**
     * @param openCode  用户 openCode
     * @param body      商品描述
     * @param total_fee 商品价格
     * @param detail    商品详情
     * @param request   request请求信息
     * @return
     */
    @RequestMapping(value = "/prepare-order", method = RequestMethod.GET)
    public BaseResult preparaOrder(String openCode, String body, int total_fee, String detail, HttpServletRequest request) {
        String apiUrl = WxPayConfig.OPEN_ID_URL + "appid="
                + WxPayConfig.APPID + "&secret="
                + WxPayConfig.APPSECRET + "&js_code="
                + openCode
                + "&grant_type=authorization_code";

        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        OpenId result = JSON.parseObject(response, OpenId.class);
        Map<String, Object> map = new HashMap<>();
        String openid = result.getOpenid();
        String appid = WxPayConfig.APPID;
        String mch_id = WxPayConfig.MCHID;
        String device_info = "WEB";
        String nonce_str = WxUtil.createRandom(true, 10);
        String attach = "妆口袋";
        map.put("appid", appid);
        map.put("openid", openid);
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
        Date date = new Date();
        String timeStamp = String.valueOf(date.getTime());

        Map<String,Object> signMap = new HashMap<>();
        signMap.put("appId",WxPayConfig.APPID);
        signMap.put("nonceStr",WxUtil.createRandom(true,10));
        signMap.put("package","prepay_id="+unifiedOrderResponseData.getPrepay_id());
        signMap.put("signType","MD5");
        signMap.put("timeStamp",timeStamp);
        String paySign = WxUtil.MD5(WxPayUtil.sort(signMap)).toUpperCase();

        System.out.println("appId:"+WxPayConfig.APPID);
        System.out.println("nonceStr:"+unifiedOrderResponseData.getNonce_str());
        System.out.println("package:prepay_id="+unifiedOrderResponseData.getPrepay_id());
        System.out.println("signType:MD5");
        System.out.println("timeStamp:"+timeStamp);

        System.out.println("最终签名:"+paySign);
        unifiedOrderResponseData.setPaySign(paySign);
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

    public static void main(String[] args) {
        System.out.println(Math.random() * 10);
    }


}
