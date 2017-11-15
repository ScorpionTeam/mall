package com.scoprion.mall.backstage.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-15 17:06
 */
public interface PointMapper {
    void updatePoint(@Param("userId") String userId, @Param("point") int pointVal);
}
