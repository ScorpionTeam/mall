package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Point;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-15 17:06
 */
@Mapper
public interface PointMapper {
    Integer updatePoint(@Param("userId") String userId,
                        @Param("pointVal") Integer pointVal);

    Point findByUserId(@Param("userId") String userId);
    
}
