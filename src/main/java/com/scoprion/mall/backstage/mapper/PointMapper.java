package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Point;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2017/10/29.
 */
@Mapper
public interface PointMapper {

    /**
     * 我的积分
     * @param userId
     * @return
     */
    Point myPoint(Long userId);

    /**
     * 积分扣减
     * @param userId
     * @param pointVal
     * @return
     */
    int minusPoint(Long userId,int pointVal);

    /**
     * 创建积分
     * @param userId
     * @param pointVal
     * @return
     */
    int addPoint(Long userId,int pointVal);

    /**
     * 更新积分
     * @param userId
     * @param pointVal
     * @return
     */
    int updatePoint(Long userId,int pointVal);

}
