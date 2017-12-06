package com.scoprion.mall.wx.rabbitmq;
import com.scoprion.constant.Constant;
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
public class SendComponent implements RabbitTemplate.ConfirmCallback {


    private static final Logger LOGGER = LoggerFactory.getLogger(SendComponent.class);

    private RabbitTemplate rabbitTemplate;

    public SendComponent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    public void send(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("发送消息：" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(Constant.EXCHANGE,Constant.ROUTING_KEY,message,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        LOGGER.info("回调信息："+correlationData.getId());
        if (b) {

        }
    }
}
