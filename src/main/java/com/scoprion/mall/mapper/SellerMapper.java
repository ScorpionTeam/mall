package com.scoprion.mall.mapper;

import com.scoprion.mall.domain.Seller;
import org.apache.ibatis.annotations.Mapper;

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
    int validByName(String sellerName, String sellerNo);

    /**
     * 删除商户
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
