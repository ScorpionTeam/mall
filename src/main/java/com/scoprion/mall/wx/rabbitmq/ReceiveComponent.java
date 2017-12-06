package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.wx.controller.WxFreeController;
import com.scoprion.mall.wx.service.pay.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
@Component
@RabbitListener(queues = Constant.QUEUE,containerFactory = "simpleRabbitListenerContainerFactory")
public class ReceiveComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveComponent.class);

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WxFreeController wxFreeController;

    @RabbitHandler
    public void process(WxOrderRequestData wxOrderRequestData) {
        LOGGER.info("接收到信息为：" + wxOrderRequestData.getWxCode());
        //wxPayService.unifiedOrder(wxOrderRequestData);
    }

    @RabbitHandler
    public void receiveFree(WxFreeOrder wxFreeOrder){
        LOGGER.info("接收到信息为:"+wxFreeOrder.getWxCode());
    }

}
