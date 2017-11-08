package com.scoprion.mall.backstage.service.good;

import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
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
     * 首页展示  限时购商品列表
     *
     * @return
     */
    List<Goods> findLimit4ByTimeGoods();

    /**
     * 查询限时购买商品   分页展示
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @return
     */
    PageResult findByPageAndLimit(int pageNo, int pageSize);

    /**
     * 创建商品
     *
     * @param goods
     * @return
     */
    BaseResult add(GoodExt goods);

    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult preferenceGiven(int pageNo, int pageSize);

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
     * 条件查询商品列表分页
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey  模糊信息
     * @param goodNo     商品编号
     * @param saleStatus 上下架
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param categoryId 类目
     * @param isHot      热销
     * @param isNew      新品
     * @param isFreight  包邮
     * @param brandId    品牌
     * @return
     */
    PageResult findByCondition(int pageNo, int pageSize, String searchKey, String goodNo, String saleStatus,
                               String startDate, String endDate, Long categoryId, String isHot, String isNew,
                               String isFreight, Long brandId);

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
    BaseResult bathDeleteGoods(List<Long> idList);

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
    BaseResult bathModifySaleStatus(String saleStatus, List<Long> goodsIdList);
}
