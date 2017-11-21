package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
@Mapper
public interface FreeMapper {


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
    int validByActivityId(@Param("activityId") Long activityId, @Param("userId") String userId);

    /**
     * 校验活动是否过期
     *
     * @param activityId
     * @return
     */
    int validByActivityIdAndDate(@Param("activityId") Long activityId);

    /**
     * 查询免费试用商品列表
     * @return
     */
    Page<Goods> findAllByFree();


}
