package com.scoprion.mall.wx.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author by kunlun
 * @created on 2017/12/6.
 */
@Component
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnCallBackListener.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info("消息重试:[message:" + message.getBody() + ",replyCode:" + replyCode, ",replyText:" + replyText + "]");
    }
}
