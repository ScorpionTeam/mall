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
     * 统一下单
     *
     * @param wxOrderRequestData
     * @param wxCode
     * @param ipAddress
     * @return
     */
    BaseResult unifiedOrder(WxOrderRequestData wxOrderRequestData);

    /**
     * 重新发起支付
     *
     * @param orderId
     * @return
     */
    BaseResult pay(Long orderId);

    /**
     * 支付成功回调
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData);

}
