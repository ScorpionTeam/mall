package com.scoprion.mall.wx.pay.util;

import com.github.pagehelper.Page;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.OrderQueryRequestData;
import com.scoprion.mall.wx.pay.domain.OrderQueryResponseData;
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

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void findOrderTasks() {
        logger.info("每60分钟执行一次。开始");
        Page<Order> page = orderMapper.findByScheduler();
        if (page == null || page.size() == 0) {
            logger.info("每60分钟执行一次。结束。");
            return;
        }
        page.forEach(order -> {
            if (order.getWxOrderNo() != null) {
                queryOrder(order.getId(), order.getWxOrderNo());
            }
            //测试代码
            //queryOrder(order.getId(), "4200000024201711093467988381");
        });
        logger.info("每60分钟执行一次。结束。");
    }

    /**
     * 商户订单号
     *
     * @param orderId
     * @param wxOrderNo
     */
    public void queryOrder(Long orderId, String wxOrderNo) {
        Map<String, Object> data = new HashMap<>(16);
        data.put("appid", WxPayConfig.APP_ID);
        data.put("mch_id", WxPayConfig.MCHID);
        data.put("transaction_id", wxOrderNo);
        data.put("nonce_str", WxUtil.createRandom(false, 32));
        String sign = WxPayUtil.sort(data);
        sign = WxUtil.MD5(sign).toUpperCase();
        data.put("sign", sign);
        String param = WxPayUtil.mapConvertToXML(data);
        String result = WxUtil.httpsRequest(WxPayConfig.WECHAT_ORDER_QUERY_URL, "POST", param);
        logger.info(result);
        OrderQueryResponseData response = WxPayUtil.castXMLStringToOrderQueryResponseData(result);
        updateLocalStatus(orderId, response.getTrade_state());
    }

    /**
     * @param orderId        订单id
     * @param responseStatus 微信返回订单状态
     *                       SUCCESS—支付成功
     *                       REFUND—转入退款
     *                       NOTPAY—未支付
     *                       CLOSED—已关闭
     *                       REVOKED—已撤销（刷卡支付）
     *                       USERPAYING--用户支付中
     *                       PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private void updateLocalStatus(Long orderId, String responseStatus) {
        /*
         * 1 待付款
         * 2 待发货
         * 3 待收货
         * 4 已完成
         * 5 申请退款中
         * 6 退款已完成
         * 7 退款拒绝
         * 8 支付失败
         * 9 正在支付中
         * 10 转入退款
         * 11 支付失败(其他原因，如银行返回失败)
         * 12 用户支付中
         * 13 已关闭
         * 14 已撤销
         */
        if (orderId == null || responseStatus == null) {
            return;
        }
        switch (responseStatus) {
            case Constant.WX_PAY_SUCCESS:
                //SUCCESS—支付成功
                orderMapper.updateOrderPayStatus(orderId, "4");
                break;
            case Constant.WX_PAY_REFUND:
                //REFUND—转入退款
                orderMapper.updateOrderPayStatus(orderId, "10");
                break;
            case Constant.WX_PAY_NOT_PAY:
                //NOTPAY—未支付
                orderMapper.updateOrderPayStatus(orderId, "1");
                break;
            case Constant.WX_PAY_CLOSED:
                //CLOSED—已关闭
                orderMapper.updateOrderPayStatus(orderId, "13");
                break;
            case Constant.WX_PAY_REVOKED:
                //REVOKED—已撤销（刷卡支付）
                orderMapper.updateOrderPayStatus(orderId, "14");
                break;
            case Constant.WX_PAY_USER_PAYING:
                // USERPAYING--用户支付中
                orderMapper.updateOrderPayStatus(orderId, "12");
                break;
            case Constant.WX_PAY_PAY_ERROR:
                //PAYERROR--支付失败(其他原因，如银行返回失败)
                orderMapper.updateOrderPayStatus(orderId, "11");
                break;
            default:
                break;
        }
    }
}
