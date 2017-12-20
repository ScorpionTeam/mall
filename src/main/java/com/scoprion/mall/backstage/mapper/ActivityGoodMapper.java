package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * Created on 2017/11/1.
 *
 * @author adming
 */
@Mapper
public interface ActivityGoodMapper {

    /**
     * 添加活动商品匹配关系
     *
     * @param activityId
     * @param goodId
     * @param status
     * @param stock
     * @return
     */
    Integer bindActivityGood(@Param("activityId") Long activityId,
                             @Param("goodId") Long goodId,
                             @Param("status") String status,
                             @Param("stock") Integer stock);


    /**
     * 根据商品id查询关联关系
     *
     * @param goodId
     * @return
     */
    ActivityGoods findByGoodIdAndActivityId(@Param("goodId") Long goodId,
                                            @Param("activityId") Long activityId);


    /**
     * 解绑商品跟活动
     *
     * @param goodId
     * @return
     */
    int unbindActivityWithGood(@Param("goodId") Long goodId);
}
