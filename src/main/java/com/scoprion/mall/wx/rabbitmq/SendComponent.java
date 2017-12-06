package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
@Component
public class SendComponent implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    private static final Logger LOGGER = LoggerFactory.getLogger(SendComponent.class);

    private RabbitTemplate rabbitTemplate;

    public SendComponent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("发送消息：" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTING_KEY, message, correlationData);
    }

    /**
     * 回调
     *
     * @param correlationData 相关数据
     * @param ack             正确应答
     * @param cause           原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("回调信息：" + correlationData.getId());
        if (ack) {

        }
    }

    /**
     * 失败重试
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }

}
