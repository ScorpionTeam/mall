package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
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
    PointLog findByUserId(@Param("userId") String userId);

    /**
     * 用户积分日志
     *
     * @param userId
     * @return
     */
    Page<PointLog> findLogPage(@Param("userId") String userId);
}
