package com.scoprion.mall.wx.pay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ycj
 * @version V1.0 <定时查询订单定时器>
 * @date 2017-11-10 10:21
 */
@Component
public class OrderScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 20000)
    public void findOrderTasks() {
        logger.info("每20秒执行一次。开始findOrderTasks");

        logger.info("每20秒执行一次。结束。");
    }
}
