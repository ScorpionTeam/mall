package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
@Mapper
public interface WxPointLogMapper {

    int add(PointLog pointLog);


    /**
     * 获取积分日志
     * @param userId
     * @return
     */
    PointLog grade(@Param("userId") String userId);


}
