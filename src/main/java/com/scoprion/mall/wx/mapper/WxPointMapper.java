package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.Point;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Order personalScore(@Param("id") Long id, @Param("userId") long userId);
}
