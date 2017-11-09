package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.domain.MallImage;
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
     * @param count
     * @return
     */
    int modifyGoodsDeduction(@Param("goodId") Long goodId, @Param("count") Integer count);

    /**
     * 更新商品信息
     *
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

    /**
     * 条件查询商品列表分页
     *
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
    Page<Goods> findByCondition(@Param("searchKey") String searchKey,
                                @Param("goodNo") String goodNo,
                                @Param("saleStatus") String saleStatus,
                                @Param("startDate") String startDate,
                                @Param("endDate") String endDate,
                                @Param("categoryId") Long categoryId,
                                @Param("isHot") String isHot,
                                @Param("isNew") String isNew,
                                @Param("isFreight") String isFreight,
                                @Param("brandId") Long brandId);

    /**
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param idList     商品id集合
     * @return 更新是否成功 1 成功  0 失败
     */
    int batchModifySaleStatus(@Param("saleStatus") String saleStatus, @Param("idList") List<Long> idList);

    /**
     * 根据商品id删除商品
     *
     * @param idList 商品id
     * @return
     */
    int batchDeleteGood(@Param("idList") List<Long> idList);


    /**
     * 修改图片对应的商品ID
     *
     * @param mallImage
     * @return
     */
    int updateImageWithGoodsId(MallImage mallImage);

    /**
     * 根据商品ID查找图片列表
     *
     * @param goodId
     * @return
     */
    List<MallImage> findImgUrlByGoodsId(@Param("goodId") Long goodId);

    /**
     * 根据商品id删除图片
     *
     * @param goodId
     * @return
     */
    int deleteImageByGoodsId(@Param("goodId") Long goodId);

    /**
     * 库存反还
     *
     * @param goodId
     * @param stock
     * @return
     */
    int updateGoodStockById(@Param("goodId") Long goodId, @Param("stock") int stock);
}
