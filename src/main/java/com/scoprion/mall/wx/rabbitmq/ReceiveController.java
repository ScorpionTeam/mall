package com.scoprion.mall.wx.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
@Component
@RabbitListener(queues = "mall.queue.message")
public class ReceiveController {

    @RabbitHandler
    public void process(String message) {
        System.out.println("接收到的消息：" + message);
    }

}
