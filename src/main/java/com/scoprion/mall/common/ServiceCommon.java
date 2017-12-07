package com.scoprion.mall.common;

import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.UserActivity;
import com.scoprion.mall.domain.WxFreeOrder;
import org.springframework.beans.BeanUtils;

/**
 * 业务公用
 *
 * @author by kunlun
 * @created on 2017/11/29.
 */
public class ServiceCommon {

    /**
     * 商品快照组装
     *
     * @param goods
     * @param goodId
     * @return
     */
    public static GoodSnapshot snapshotConstructor(Goods goods, Long goodId) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goodId);
        goodSnapshot.setGoodDescription(goods.getDescription());
        return goodSnapshot;
    }


    /**
     * 组装参加活动记录
     * @param wxFreeOrder
     * @param userId
     * @return
     */
    public static UserActivity userActivityConstructor(WxFreeOrder wxFreeOrder, String userId){
        UserActivity userActivity = new UserActivity();
        userActivity.setActivityId(wxFreeOrder.getActivityId());
        userActivity.setGoodId(wxFreeOrder.getActivityId());
        userActivity.setUserId(userId);
        return userActivity;
    }


}
