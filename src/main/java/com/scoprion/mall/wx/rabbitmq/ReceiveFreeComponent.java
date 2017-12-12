package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.service.free.WxFreeService;
import com.scoprion.mall.wx.service.pay.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author by hmy
 * @created on 2017/12/6/006.
 */

@Component
public class ReceiveFreeComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveFreeComponent.class);

    @Autowired
    private WxFreeService wxFreeService;

    @RabbitHandler
    @RabbitListener(queues = Constant.QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void processFree(WxFreeOrder wxFreeOrder) {
        LOGGER.info("接收到信息为：" + wxFreeOrder.getWxCode());
        //wxPayService.unifiedOrder(wxOrderRequestData);
    }

}
