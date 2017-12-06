package com.scoprion.mall.wx.mapper;


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
    Point findByUserId(@Param("userId") String userId);


    /**
     * 修改积分
     *
     * @param point
     * @return
     */
    int level(Point point);

    /**
     * 新增积分记录
     *
     * @param point
     * @return
     */
    int add(Point point);
}
