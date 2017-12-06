package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.GoodSnapshot;
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
public class SendSnapshotComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendSnapshotComponent.class);

    private RabbitTemplate rabbitTemplate;

    public SendSnapshotComponent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(GoodSnapshot goodSnapshot) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("发送消息：" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTING_KEY, goodSnapshot,
                correlationData);
    }
}
