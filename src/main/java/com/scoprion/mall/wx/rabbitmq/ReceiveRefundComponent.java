package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-11 15:05
 */
@Component
public class ReceiveRefundComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveRefundComponent.class);

    @Autowired
    WxOrderMapper wxOrderMapper;

    @Autowired
    WxOrderLogMapper wxOrderLogMapper;

    @RabbitHandler
    @RabbitListener(queues = Constant.REFUND_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void processOrder(Order order) {
        LOGGER.info("接收到信息为：" + order.toString());
        String nonceStr = WxUtil.createRandom(false, 10);
        String refundOrderNo = order.getOrderNo() + "T";
        //定义接收退款返回字符串
        String refundXML = WxPayUtil.refundSign(order.getOrderNo(), order.getPaymentFee(), order.getRefundFee(),
                refundOrderNo, nonceStr);
        //接收退款返回
        String response = null;
        try {
            response = WxPayUtil.doRefund(WxPayConfig.WECHAT_REFUND, refundXML, WxPayConfig.MCHID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response != null) {
            WxRefundNotifyResponseData responseData = WxPayUtil.castXMLStringToWxRefundNotifyResponseData(response);
            Boolean result = "success".equalsIgnoreCase(responseData.getReturn_code());
            if (result) {
                //退款成功，更新状态
                wxOrderMapper.updateOrderStatusById(order.getId(), CommonEnum.REFUND_SUCCESS.getCode());
            }
            //记录退款日志
            ServiceCommon.saveWxOrderLog(order.getId(), order.getAddress(), order.getOrderNo(),
                    responseData.getReturn_msg(), wxOrderLogMapper);
        } else {
            //记录退款失败日志
            ServiceCommon.saveWxOrderLog(order.getId(), order.getAddress(), order.getOrderNo(),
                    "微信退款失败", wxOrderLogMapper);
        }
    }
}
