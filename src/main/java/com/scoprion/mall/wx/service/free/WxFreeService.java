package com.scoprion.mall.wx.service.free;



import com.scoprion.mall.domain.WxFreeOrder;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by hmy
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
     * @return
     */
    BaseResult apply(WxFreeOrder wxFreeOrder);

    /**
     * 微信支付
     *
     * @param orderId
     * @param activityId
     * @param goodId
     * @return
     */
    BaseResult pay(Long orderId,Long activityId,Long goodId);

}
