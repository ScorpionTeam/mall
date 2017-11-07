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
}
