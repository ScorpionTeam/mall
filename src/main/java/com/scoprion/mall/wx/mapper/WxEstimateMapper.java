package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Estimate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface WxEstimateMapper {


    /**
     * 商品评价列表
     *
     * @param goodId
     * @param userId
     * @return
     */
    Page<Estimate> findPage(@Param("goodId") Long goodId,
                            @Param("userId") String userId);

}
