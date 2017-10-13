package com.scoprion.mall.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    int add(Good good);

    /**
     * 优选  分页查询
     *
     * @return
     */
    Page<Good> preferenceGivenByPage();

    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    Good findById(@Param("goodId") Long goodId);


}
