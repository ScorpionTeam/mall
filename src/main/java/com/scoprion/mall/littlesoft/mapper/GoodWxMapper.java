package com.scoprion.mall.littlesoft.mapper;

import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface GoodWxMapper {

    List<Good> goodDetail(@Param("goodId") Long goodId);
}
