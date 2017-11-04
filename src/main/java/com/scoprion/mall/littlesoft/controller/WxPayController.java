package com.scoprion.mall.littlesoft.controller;

import com.alibaba.fastjson.JSON;
import com.scoprion.constant.Constant;
import com.scoprion.result.BaseResult;
import com.scoprion.wxpay.OpenId;
import com.scoprion.wxpay.WxPayConfig;
import com.scoprion.wxpay.WxUtil;
import com.scoprion.wxpay.domain.UnifiedOrderRequestData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
@RestController
@RequestMapping("wx")
public class WxPayController {


    @RequestMapping(value = "/prepare-order", method = RequestMethod.GET)
    public BaseResult preparaOrder(String code) {
        String apiUrl = WxPayConfig.OPEN_ID_URL + "appid="
                + WxPayConfig.APPID + "&secret="
                + WxPayConfig.APPSECRET + "&js_code="
                + code
                + "&grant_type=authorization_code";

        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        OpenId result = JSON.parseObject(response, OpenId.class);
        String openid = result.getOpenid();
        String appid = WxPayConfig.APPID;
        String mch_id = WxPayConfig.MCHID;
        String device_info = "1000";
        String nonce_str = WxUtil.createRandom(true, 10);
        String body = "雅诗兰黛面霜";
        int totalFee = 100;
        String detail = "雅诗兰黛面霜描述";
        String attach = "妆口袋";
        String stringSignTem = "appid=" + appid + "&body=" + body + "&device_info=" + device_info + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str;
        String sign = WxUtil.MD5(stringSignTem);
        UnifiedOrderRequestData.UnifiedOrderReqDataBuilder unifiedOrderReqDataBuilder = new UnifiedOrderRequestData.UnifiedOrderReqDataBuilder(body,"100000000",totalFee,"192.168.0.1",null);
        UnifiedOrderRequestData unifiedOrderRequestData = new UnifiedOrderRequestData(unifiedOrderReqDataBuilder);

        return null;

    }
}
