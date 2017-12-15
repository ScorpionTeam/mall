package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import com.scoprion.mall.domain.good.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author fk
 * @create on 2017/11/12.
 */
@Mapper
public interface WxActivityMapper {

    /**
     * 拼团列表
     *
     * @param activityType
     * @return
     */
    Page<Activity> groupList(@Param("activityType") String activityType);

    /**
     * 查询试用列表
     *
     * @return
     */
    Page<Activity> findAll();

    /**
     * 查询拼团详情
     *
     * @param activityId
     * @return
     */
    Activity findById(@Param("activityId") Long activityId);


    /**
     * 获取商品详情
     *
     * @param goodId
     * @return
     */
    Goods findByGoodId(@Param("goodId") Long goodId);

    /**
     * 查询活动商品库存
     *
     * @param goodId
     * @return
     */
    ActivityGoods findByActivityGoodStock(@Param("goodId") Long goodId);

    /**
     * 判断活动库存
     *
     * @param activityId
     * @param goodId
     * @return
     */
    ActivityGoods findByActivityIdAndGoodId(@Param("activityId") Long activityId, @Param("goodId") Long goodId);

}
