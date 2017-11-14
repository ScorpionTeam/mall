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
    Point findByUserId(@Param("userId") String userId);



    /**
     * 修改积分
     * @param point
     * @return
     */
    int level(Point point);

}
