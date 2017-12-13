package com.scoprion.mall.backstage.service.good;

import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.request.GoodRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
public interface GoodsService {

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
    BaseResult findByGoodId(Long goodId);

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
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param goodId     商品id
     * @return
     */
    BaseResult modifySaleStatus(String saleStatus, Long goodId);

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    BaseResult deleteGoodsById(Long id);

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
    BaseResult modifyGoodsDeduction(Long id, Integer count);

    /**
     * 批量商品上下架
     *
     * @param saleStatus  1上架 0下架 默认上架
     * @param goodsIdList 商品id集合
     * @return
     */
    BaseResult batchModifySaleStatus(String saleStatus, List<Long> goodsIdList);

    /**
     * 新创建商品审核
     * @param audit
     * @param reason
     * @param id
     * @return
     */
    BaseResult auditGood(String audit, String reason, Long id);

}
