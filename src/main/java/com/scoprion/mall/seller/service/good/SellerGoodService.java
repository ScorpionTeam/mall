package com.scoprion.mall.seller.service.good;

import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.request.GoodRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:27
 */
public interface SellerGoodService {
    /**
     * 创建商品
     *
     * @param goods
     * @return
     */
    BaseResult add(GoodExt goods);


    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    BaseResult findById(Long goodId);

    /**
     * 根据id修改商品信息
     *
     * @param goods
     * @return
     */
    BaseResult updateGood(GoodExt goods);

    /**
     * ss
     *
     * @param goodRequestParams
     * @return
     */
    PageResult findByCondition(GoodRequestParams goodRequestParams);

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    BaseResult deleteGoodById(Long id);

    /**
     * 批量删除商品
     *
     * @param idList 商品id集合
     * @return
     */
    BaseResult batchDeleteGood(List<Long> idList);

    /**
     * 商品库存减扣
     *
     * @param id    商品id--主键
     * @param count 扣减、增加数量
     * @return BaseResult
     */
    BaseResult updateGoodStock(Long id, Integer count);

    /**
     * 批量商品上下架
     *
     * @param saleStatus  1上架 0下架 默认上架
     * @param goodsIdList 商品id集合
     * @return
     */
    BaseResult batchModifySaleStatus(String saleStatus, List<Long> goodsIdList);
}
