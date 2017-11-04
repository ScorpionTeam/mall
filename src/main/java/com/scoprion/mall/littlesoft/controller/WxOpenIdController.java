package com.scoprion.mall.littlesoft.controller;

import com.alibaba.fastjson.JSON;
import com.scoprion.constant.Constant;
import com.scoprion.wxpay.OpenId;
import com.scoprion.result.BaseResult;
import com.scoprion.wxpay.WxPayConfig;
import com.scoprion.wxpay.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
//@RestController
//@RequestMapping("wx")
public class WxOpenIdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxOpenIdController.class);

//    /**
//     * 获取微信openid
//     *
//     * @param code
//     * @return
//     */
//    @RequestMapping(value = "/openid", method = RequestMethod.GET)
//    public BaseResult getOpenId(String code) {
//        String apiUrl = WxPayConfig.OPEN_ID_URL + "appid="
//                + Constant.APP_ID + "&secret="
//                + Constant.SECRET + "&js_code="
//                + code
//                + "&grant_type=authorization_code";
//        String response = WxUtil.httpsRequest(apiUrl,"GET",null);
//        OpenId openId = JSON.parseObject(response, OpenId.class);
//        System.out.println("返回信息------>" + response);
//        return BaseResult.success(openId);
//    }
}
