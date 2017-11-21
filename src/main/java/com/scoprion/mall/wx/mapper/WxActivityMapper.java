package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fk
 *
 * @create on 2017/11/12.
 */
@Mapper
public interface WxActivityMapper {
    /**
     * 拼团
     *
     * @return
     */
    Page<Activity> findByGroup();

    /**
     * 秒杀
     *
     * @return
     */
    Page<Activity> secKill();

    /**
     * 优选
     *
     * @return
     */
    Page<Activity> preference();

    /**
     * 根据活动类型查找商品(拼团 activityType = 2)
     *
     * @return
     */
    Activity findByActivityTypeTwo();

    /**
     * 根据活动类型查找商品(秒杀 activityType = 1)
     *
     * @return
     */
    Activity findByActivityTypeOne();

    /**
     * 根据活动类型查找商品(优选 activityType = 3)
     *
     * @return
     */
    Activity findByActivityTypeThree();
}
