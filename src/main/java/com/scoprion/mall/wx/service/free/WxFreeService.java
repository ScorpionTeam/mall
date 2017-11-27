package com.scoprion.mall.wx.service.free;


import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.DeliveryExt;
import com.scoprion.mall.domain.OrderExt;
import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
public interface WxFreeService {

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findAll(Integer pageNo, Integer pageSize);

    /**
     * 参加试用
     * @param wxFreeOrder
     * @param ipAddress
     * @return
     */
    BaseResult apply(WxFreeOrder wxFreeOrder, String ipAddress);

    /**
     * 微信支付
     * @param wxCode
     * @param orderId
     * @return
     */
    BaseResult pay(String wxCode,Long orderId);

    /**
     * 支付成功回调
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData);
}
