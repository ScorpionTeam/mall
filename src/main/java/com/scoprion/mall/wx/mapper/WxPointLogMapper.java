package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.PointLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
@Mapper
public interface WxPointLogMapper {

    int add(PointLog pointLog);

    
}
