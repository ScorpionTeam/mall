package com.scoprion.mall.backstage.service.good;

import com.scoprion.mall.domain.Good;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/9/29.
 * @author adming
 */
public interface GoodService {

    /**
     * 首页展示  限时购商品列表
     *
     * @return
     */
    List<Good> findLimit4ByTimeGoods();

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
     * @param good
     * @return
     */
    BaseResult add(Good good);

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
    BaseResult goodInfo(Long goodId);

    /**
     * 根据id修改商品信息
     *
     * @param good
     * @return
     */
    BaseResult update(Good good);

    /**
     * 根据条件差查询商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult findByCondition(int pageNo, int pageSize, String searchKey);

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
    BaseResult deleteGoodById(Long id);

}