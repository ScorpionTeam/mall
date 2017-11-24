package com.scoprion.mall.wx.service.pay;

import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
public interface WxPayService {


    /**
     * 微信预下单
     *
     * @param wxOrderRequestData
     * @param wxCode
     * @param ipAddress
     * @return
     */
    BaseResult preOrder(WxOrderRequestData wxOrderRequestData, String wxCode, String ipAddress);

    /**
     * 调起支付
     *
     * @param wxCode
     * @param orderId
     * @return
     */
    BaseResult pay(String wxCode, Long orderId);

    /**
     * 支付成功回调
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData);

    /**
     * @param goodId     商品id
     * @param deliveryId 收件人id
     * @param buyNum     购买数量
     * @param message    买家留言
     * @param orderType  订单类型
     * @param useTicket  是否使用优惠券
     * @param paymentFee 实付金额
     * @param orderFee   订单金额
     * @param reduceFee  优惠金额
     * @param freightFee 运费
     * @param goodFee    商品金额
     * @return
     */
    BaseResult pressureTest(Long goodId, Long deliveryId,
                            int buyNum, String message,
                            String orderType, String useTicket,
                            int paymentFee, int orderFee,
                            int reduceFee, int freightFee,
                            int goodFee);

}
