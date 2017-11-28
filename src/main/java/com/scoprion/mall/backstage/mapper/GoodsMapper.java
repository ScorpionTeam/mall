package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.mall.domain.request.GoodRequestParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@Mapper
public interface GoodsMapper {

    /**
     * 首页展示 查询限时购买商品   4条
     *
     * @return
     */
    List<Goods> findLimit4ByTimeGoods();

    /**
     * 分页查询 限时购买商品列表
     *
     * @return
     */
    Page<Goods> findByPageAndLimit();

    /**
     * 创建商品
     *
     * @param goods
     * @return
     */
    int add(Goods goods);

    /**
     * 优选  分页查询
     *
     * @return
     */
    Page<Goods> preferenceGivenByPage();

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
    int modifyGoodsDeduction(@Param("goodId") Long goodId,
                             @Param("count") Integer count);

    /**
     * 更新商品信息
     *
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

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
    int batchModifySaleStatus(@Param("saleStatus") String saleStatus,
                              @Param("idList") List<Long> idList);

    /**
     * 根据商品id删除商品
     *
     * @param idList 商品id
     * @return
     */
    int batchDeleteGood(@Param("idList") List<Long> idList);

    /**
     * 库存反还
     *
     * @param goodId
     * @param stock
     * @return
     */
    int updateGoodStockById(@Param("goodId") Long goodId,
                            @Param("stock") int stock);

    /**
     * 选择绑定活动的商品列表
     *
     * @param searchKey 模糊信息
     * @return
     */
    List<GoodExt> findForActivity(@Param("searchKey") String searchKey);

    /**
     * ss
     * @param requestParams
     * @return
     */
    List<GoodExt> findByActivityId(GoodRequestParams requestParams);
}
