package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Store;
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
     *
     * @param audit
     * @param searchKey
     * @return
     */
    Page<Store> findPage(@Param("audit") String audit,
                         @Param("searchKey") String searchKey);

    /**
     * 修改审核状态
     *
     * @param audit
     * @param reason
     * @param id
     * @return
     */
    Integer update(@Param("audit") String audit,
                   @Param("reason") String reason,
                   @Param("id") Long id);

    /**
     * 店铺
     * @param id 店铺id
     * @return
     */
    Store findById(@Param("id") Long id);
}
