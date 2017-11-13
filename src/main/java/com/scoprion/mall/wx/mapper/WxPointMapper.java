package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.ActivityExt;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
@Mapper
public interface WxPointMapper {

    /**
     * 我的积分
     *
     * @param userId
     * @return
     */
    Point findByUserId(String userId);

    /**
     * 修改我的积分
     *
     * @param userId
     * @param currentPoint
     * @return
     */
    int updatePoint(@Param("userId") String userId, @Param("currentPoint") int currentPoint);

    /**
     * 获取详情
     * @param userId
     * @return
     */
    Point grades(@Param("userId") String userId);

    /**
     * 等级划分
     * @param point
     * @return
     */
    int level(Point point);

}
