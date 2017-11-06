package com.scoprion.mall.littlesoft.service.pay;

import com.scoprion.mall.domain.WxOrderRequestData;
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
}
