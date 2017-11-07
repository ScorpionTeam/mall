package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
@Mapper
public interface WxGoodMapper {

    /**
     * 查询商品详情
     *
     * @param goodId
     * @return
     */
    Good findById(@Param("goodId") Long goodId);
    
    /**
     * 库存扣减
     *
     * @param goodId
     * @param stock
     * @return
     */
    int updateGoodStockById(@Param("goodId") Long goodId, @Param("stock") int stock);

    Page<Good> findOnline();
}
