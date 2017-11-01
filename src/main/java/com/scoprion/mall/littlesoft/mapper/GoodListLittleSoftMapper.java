package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by admin1 on 2017/11/1.
 */
@Mapper
public interface GoodListLittleSoftMapper {

    /**
     * 秒杀活动商品列表
     *
     * @return
     */
    Page<Good> secondList();

    /**
     * 团购商品列表
     *
     * @return
     */
    Page<Good> groupList();

    /**
     * 精选商品列表
     *
     * @return
     */
    Page<Good> wellChoilceList();

}
