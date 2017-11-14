package com.scoprion.mall.wx.pay.util;

import com.github.pagehelper.Page;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.OrderLog;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.OrderQueryResponseData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.service.pay.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycj
 * @version V1.0 <定时查询订单定时器>
 * @date 2017-11-10 10:21
 */
@Component
public class WxOrderScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    WxPayService wxPayService;

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    public void findOrderTasks() {
        logger.info("每4小時执行一次。开始");
        Page<Order> page = orderMapper.findByScheduler();
        if (page == null || page.size() == 0) {
            logger.info("每4小時执行一次。结束。");
            return;
        }
        page.forEach(order -> {
            if (order.getWxOrderNo() != null) {
                queryOrder(order.getId(), order.getWxOrderNo(), null);
            } else if (order.getOrderNo() != null) {
                queryOrder(order.getId(), null, order.getOrderNo());
            }
        });
        logger.info("每4小時执行一次。结束。");
    }

    /**
     * 商户订单号
     *
     * @param orderId   订单id
     * @param orderNo   订单号
     * @param wxOrderNo 微信订单号
     */
    private void queryOrder(Long orderId, String wxOrderNo, String orderNo) {
        Map<String, Object> data = new HashMap<>(16);
        data.put("appid", WxPayConfig.APP_ID);
        data.put("mch_id", WxPayConfig.MCHID);
        if (wxOrderNo != null) {
            data.put("transaction_id", wxOrderNo);
        } else {
            data.put("out_trade_no", orderNo);
        }
        data.put("nonce_str", WxUtil.createRandom(false, 32));
        String sign = WxPayUtil.sort(data);
        sign = WxUtil.MD5(sign).toUpperCase();
        data.put("sign", sign);
        String param = WxPayUtil.mapConvertToXML(data);
        String result = WxUtil.httpsRequest(WxPayConfig.WECHAT_ORDER_QUERY_URL, "POST", param);
        logger.info(result);
        OrderQueryResponseData response = WxPayUtil.castXMLStringToOrderQueryResponseData(result);
        updateLocalStatus(orderId, response);
    }

    /**
     * @param orderId      订单id
     * @param responseData 微信返回
     */
    private void updateLocalStatus(Long orderId, OrderQueryResponseData responseData) {
        if (orderId == null || responseData.getTrade_state() == null) {
            return;
        }
        switch (responseData.getTrade_state()) {
            case Constant.WX_PAY_SUCCESS:
                //SUCCESS—支付成功
                UnifiedOrderNotifyRequestData data = new UnifiedOrderNotifyRequestData();
                data.setTransaction_id(responseData.getTransaction_id());
                data.setOut_trade_no(responseData.getOut_trade_no());
                data.setTime_end(responseData.getTime_end());
                wxPayService.callback(data);
                break;
            default:
                break;
        }
    }
}
