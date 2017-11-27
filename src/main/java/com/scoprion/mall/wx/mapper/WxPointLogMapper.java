package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
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

    /**
     * 增加
     *
     * @param pointLog
     * @return
     */
    int add(PointLog pointLog);


    /**
     * 获取积分日志
     *
     * @param userId
     * @return
     */
    PointLog grade(@Param("userId") String userId);

    /**
     * 用户积分日志
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    Page<PointLog> pointLog(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("userId") String userId);
}
