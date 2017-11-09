package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@Mapper
public interface OrderLogMapper {
    /**
     * 添加日志
     * @param orderLog
     */
    void add(OrderLog orderLog);
}
