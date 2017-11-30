package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fk
 *
 * @create on 2017/11/12.
 */
@Mapper
public interface WxActivityMapper {
    /**
     * 拼团列表
     *
     * @return
     */
    Page<Activity> groupList(@Param("activity_type") String activity_type);

    /**
     * 根据活动类型查询商品
     *
     * @param activity_type
     * @return
     */
    Activity findByActivityType(@Param("activity_type") String activity_type);

    /**
     * 查询试用列表
     *
     * @return
     */
    Page<Activity> findAll();

    /**
     * 参加拼团商品详细信息
     *
     * @param activityGoodId
     * @return
     */
    ActivityGoods findByActivityGoodId(@Param("activityGoodId") Long activityGoodId);

    /**
     * 判断是否参加过此活动
     *
     * @param activityId
     * @param userId
     * @return
     */
    int validByActivityId(@Param("activityId") Long activityId,
                          @Param("userId") String userId);

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

    ActivityGoods findByActivityIdAndGoodId(@Param("activityId") Long activityId, @Param("goodId") Long goodId);

}
