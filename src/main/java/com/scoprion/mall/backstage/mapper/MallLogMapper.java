package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.MallLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2017/10/14.
 */
@Mapper
public interface MallLogMapper {

    /**
     * 创建日志
     * @param mallLog
     * @return
     */
    int add(MallLog mallLog);
}
