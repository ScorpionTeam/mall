package com.scoprion.mall.good.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Mapper
public interface GoodMapper {

    /**
     * 首页展示 查询限时购买商品   4条
     *
     * @return
     */
    List<Good> findLimit4ByTimeGoods();

    /**
     * 分页查询 限时购买商品列表
     *
     * @return
     */
    Page<Good> findByPageAndLimit();


}
