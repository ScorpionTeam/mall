package com.scoprion.mall.seller.mapper;

import com.scoprion.mall.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/12/7.
 */

@Mapper
public interface SellerMapper {

    /**
     * 创建商铺
     * @param seller
     * @return
     */
    Integer add (Seller seller);

    /**
     * 删除商铺
     * @param id
     * @return
     */
    Integer delete(@Param("id") Long id);

    /**
     * 修改商铺信息
     * @param seller
     * @return
     */
    Integer modify(Seller seller);

    /**
     * 校验名字是否存在
     * @param sellerName
     * @return
     */
    Integer validByName(@Param("sellerName") String sellerName);

    /**
     * 校验该商户是否已经存在商铺
     * @param userId
     * @return
     */
    Integer validByUserId(@Param("userId") String userId);
}
