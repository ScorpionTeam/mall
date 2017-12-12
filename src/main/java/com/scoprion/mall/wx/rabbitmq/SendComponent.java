package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
@Component
public class SendComponent {


    private static final Logger LOGGER = LoggerFactory.getLogger(SendComponent.class);

    private RabbitTemplate rabbitTemplate;

    public SendComponent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
//        this.rabbitTemplate.setConfirmCallback(this);
    }

    public void send(WxOrderRequestData wxOrderRequestData) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("发送消息：" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTING_KEY, wxOrderRequestData,
                correlationData);
    }

    public void sendRefundingOrder(Order order) {
        LOGGER.info("发送消息：" + order.toString());
        this.rabbitTemplate.convertAndSend(Constant.REFUND_EXCHANGE, Constant.REFUND_ROUTING_KEY, order);
    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        LOGGER.info("回调信息：" + correlationData.getId());
//        if (b) {
//
//        }
//    }

}
