package com.scoprion.mall.seller.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.request.GoodRequestParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:26
 */
@Mapper
public interface SellerGoodMapper {

    /**
     * 创建商品
     *
     * @param goods
     * @return
     */
    Integer add(Goods goods);

    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    GoodExt findById(@Param("goodId") Long goodId);

    /**
     * 商品库存扣减
     *
     * @param goodId
     * @param count  正数为添加、负数为扣减
     * @return
     */
    Integer updateGoodStock(@Param("goodId") Long goodId,
                            @Param("count") Integer count);

    /**
     * 更新商品信息
     *
     * @param goods
     * @return
     */
    Integer updateGood(Goods goods);

    /**
     * d
     *
     * @param goodRequestParams
     * @return
     */
    Page<GoodExt> findByCondition(GoodRequestParams goodRequestParams);

    /**
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param idList     商品id集合
     * @return 更新是否成功 1 成功  0 失败
     */
    Integer batchModifySaleStatus(@Param("saleStatus") String saleStatus,
                                  @Param("idList") List<Long> idList);

    /**
     * 根据商品id删除商品
     *
     * @param idList 商品id
     * @return
     */
    Integer batchDeleteGood(@Param("idList") List<Long> idList);

    /**
     * 未绑定活动的商品列表
     *
     * @param requestParams
     * @return
     */
    Page<GoodExt> findForActivity(GoodRequestParams requestParams);

    /**
     * 根据活动id作为主要条件查询列表
     *
     * @param requestParams
     * @return
     */
    Page<GoodExt> findByActivityId(GoodRequestParams requestParams);

    /**
     * ss
     *
     * @param requestParams
     * @return
     */
    Page<GoodExt> findForCategory(GoodRequestParams requestParams);
}
