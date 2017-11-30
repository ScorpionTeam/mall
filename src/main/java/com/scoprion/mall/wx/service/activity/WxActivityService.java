package com.scoprion.mall.wx.service.activity;

import com.scoprion.mall.domain.WxGroupOrder;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created by fk
 *
 * @create on 2017/11/12.
 */
public interface WxActivityService {

    /**
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult groupList(int pageNo, int pageSize, String activity_type);

    /**
     * 参加拼团
     *
     * @param wxGroupOrder
     * @param ipAddress
     * @return
     */
    BaseResult joinGroup(WxGroupOrder wxGroupOrder, String ipAddress);

    /**
     * 微信支付
     *
     * @param orderId
     * @param activityId
     * @param goodId
     * @return
     */
    BaseResult pay(Long orderId, Long activityId, Long goodId);

}
