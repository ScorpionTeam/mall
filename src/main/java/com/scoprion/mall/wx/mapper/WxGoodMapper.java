package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by hmy
 * @created on 2017/11/7.
 */
@Mapper
public interface WxGoodMapper {

    /**
     * 首页商品列表
     * @return
     */
    Page<Goods> findOnline();

    /**
     * 查询商品详情
     *
     * @param goodId
     * @return
     */
    GoodExt findById(@Param("goodId") Long goodId);

    /**
     * 访问量
     * @param goodId
     */
    void updateVisitTotal(@Param("goodId") Long goodId);

    /**
     * 库存扣减
     *
     * @param goodId
     * @param stock
     * @return
     */
    int updateGoodStockById(@Param("goodId") Long goodId, @Param("stock") int stock);

    /**
     * 修改销量
     *
     * @param saleVolume
     * @param goodId
     * @return
     */
    int updateSaleVolume(@Param("saleVolume") int saleVolume, @Param("goodId") Long goodId);

    /**
     * 商品搜索
     * @param searchKey
     * @return
     */
    Page<Goods> findBySearchKey(@Param("searchKey") String searchKey);
}
