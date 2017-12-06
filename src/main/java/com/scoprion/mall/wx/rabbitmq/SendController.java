package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
@RestController
@RequestMapping("/mq")
public class SendController {

    @Autowired
    private SendComponent sendComponent;

    @RequestMapping(value = "/sendMessage/{message}", method = RequestMethod.GET)
    public BaseResult sendMessage(@PathVariable String message) {
        System.out.println("即将发送的消息：" + message);
        for (int i = 0; i < 2; i++) {
            WxOrderRequestData wxOrderRequestData = new WxOrderRequestData();
            wxOrderRequestData.setWxCode("432536457658697");
            wxOrderRequestData.setGoodFee(200);
            sendComponent.send(wxOrderRequestData);
        }
        return BaseResult.success("消息发送成功");
    }

}
