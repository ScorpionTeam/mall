package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/10/10.
 */
@Mapper
public interface SellerMapper {

    /**
     * 创建商户
     *
     * @param seller
     * @return
     */
    int add(Seller seller);

    /**
     * 编辑商户
     *
     * @param seller
     * @return
     */
    int edit(Seller seller);

    /**
     * 校验商户名称是否存在
     *
     * @param sellerName
     * @return
     */
    int validByNameAndSellerNo(@Param("sellerName") String sellerName, @Param("sellerNo") String sellerNo);

    /**
     * 校验商户名称是否存在
     *
     * @param sellerName
     * @return
     */
    int validByName(@Param("sellerName") String sellerName);

    /**
     * 删除商户
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 分页查询店铺列表
     *
     * @param sellerName
     * @return
     */
    Page<Seller> listByPage(String sellerName);
}
