package com.scoprion.mall.littlesoft.mapper;

import com.scoprion.mall.domain.Good;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface GoodWxMapper {

    /**
     * 查询商品详情
     *
     * @param goodId
     * @return
     */
    Good goodDetail(@Param("goodId") Long goodId);

    /**
     * 商品扣减
     *
     * @param goodId
     * @param purchase
     * @return
     */
    Integer goodDeduct(@Param("goodId") Long goodId, @Param("purchase") Integer purchase);
}
