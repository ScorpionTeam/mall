package com.scoprion.mall.wx.mapper;

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
     * 个人积分详情
     * @param id
     * @param userId
     * @return
     */
    PointLog personalScore(@Param("id") Long id,@Param("userId") String userId);


    /**
     * 修改积分
     * @param userId
     * @param currentPoint
     * @return
     */
    int personal(@Param("userId") String userId, @Param("currentPoint") Integer currentPoint);

}
