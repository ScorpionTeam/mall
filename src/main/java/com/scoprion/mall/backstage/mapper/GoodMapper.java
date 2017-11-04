package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@Mapper
public interface GoodMapper {

    /**
     * 首页展示 查询限时购买商品   4条
     *
     * @return
     */
    List<Good> findLimit4ByTimeGoods();

    /**
     * 分页查询 限时购买商品列表
     *
     * @return
     */
    Page<Good> findByPageAndLimit();

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    int add(Good good);

    /**
     * 优选  分页查询
     *
     * @return
     */
    Page<Good> preferenceGivenByPage();

    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    Good findById(@Param("goodId") Long goodId);

    /**
     * 商品库存扣减
     *
     * @param goodId
     * @return
     */
    int goodDeduction(@Param("goodId") Long goodId);

    /**
     * 更新商品信息
     *
     * @param good
     * @return
     */
    int updateGood(Good good);

    /**
     * 根据条件模糊查询
     *
     * @param searchKey String
     * @return
     */
    Page<Good> findByCondition(@Param("searchKey") String searchKey);

    /**
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param goodId     商品id
     * @return 更新是否成功 1 成功  0 失败
     */
    int modifySaleStatus(@Param("saleStatus") String saleStatus, @Param("goodId") Long goodId);

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    int deleteGoodById(@Param("id") Long id);
}
