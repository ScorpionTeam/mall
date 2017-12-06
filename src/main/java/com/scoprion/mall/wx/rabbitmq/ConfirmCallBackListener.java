package com.scoprion.mall.wx.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * @author by kunlun
 * @created on 2017/12/6.
 */
@Component
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmCallBackListener.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        LOGGER.info("进入回调信息：[ack:" + ack + ",cause:" + cause + "]");
    }

}
