package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author by hmy
 * @created on 2017/11/7.
 */
@Mapper
public interface WxGoodMapper {

    /**
     * 首页商品列表
     *@param categoryId
     * @return
     */
    Page<Goods> findHomePage(@Param("categoryId") Long categoryId);

    /**
     * 查询商品详情
     *
     * @param goodId
     * @return
     */
    GoodExt findById(@Param("goodId") Long goodId);

    /**
     * 访问量
     *
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
    int updateGoodStockById(@Param("goodId") Long goodId,
                            @Param("stock") int stock);

    /**
     * 修改销量
     *
     * @param saleVolume
     * @param goodId
     * @return
     */
    int updateSaleVolume(@Param("saleVolume") int saleVolume,
                         @Param("goodId") Long goodId);

    /**
     * 商品搜索
     *
     * @param searchKey
     * @param categoryId
     * @return
     */
    Page<Goods> findBySearchKey(@Param("categoryId") Long categoryId,
                                @Param("searchKey") String searchKey);

    /**
     * 获取商品快照详情
     * @param orderId
     * @return
     */
    GoodSnapshot findByGoodSnapshotDetail(@Param("orderId") Long orderId);

    /**
     * 活动(拼团)商品库存扣减
     * @param id
     * @param count
     */
    void updateActivityGoodStockById(@Param("id") Long id, @Param("count") int count);
}
