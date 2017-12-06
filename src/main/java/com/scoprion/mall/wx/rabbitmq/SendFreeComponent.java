package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.WxFreeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author by hmy
 * @created on 2017/12/6/006.
 */

@Component
public class SendFreeComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendFreeComponent.class);

    private RabbitTemplate rabbitTemplate;

    public SendFreeComponent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
//        this.rabbitTemplate.setConfirmCallback(this);
    }

    public void send(WxFreeOrder WxFreeOrder) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("发送消息：" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTING_KEY, WxFreeOrder,
                correlationData);
    }
}
