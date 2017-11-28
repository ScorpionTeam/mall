package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by hmy
 * @created on 2017/11/20.
 */
@Mapper
public interface WxFreeMapper {


    /**
     * 查询活动详情
     *
     * @param activityId
     * @return
     */
    Activity findById(@Param("activityId") Long activityId);

    /**
     * 查询是否参加过该活动
     *
     * @param activityId
     * @param userId
     * @return
     */
    int validByActivityId(@Param("activityId") Long activityId,
                          @Param("userId") String userId);

    /**
     * 查询活动商品详情
     *
     * @param activityGoodId
     * @return
     */
    ActivityGoods findByActivityGoodId(@Param("activityGoodId") Long activityGoodId);


    /**
     * 获取商品详情
     *
     * @param goodId
     * @return
     */
    Goods findByGoodId(@Param("goodId") Long goodId);

}
