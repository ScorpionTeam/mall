package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Mapper
public interface WxGoodsMapper {

    /**
     * 获取上架商品
     *
     * @return
     */
    Page<Goods>findAll();

    /**
     * 根据id获取商品详情
     *
     * @param id
     * @return
     */
    Goods goodsDetail(@Param("id")Long id);
}
