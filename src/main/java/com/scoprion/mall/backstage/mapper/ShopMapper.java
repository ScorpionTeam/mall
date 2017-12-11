package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */

@Mapper
public interface ShopMapper {


    /**
     * 获取店铺申请列表
     * @param audit
     * @return
     */
    Page<Seller>listPage(@Param("audit") String audit);
}
