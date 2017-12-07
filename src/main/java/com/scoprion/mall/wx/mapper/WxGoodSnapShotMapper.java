package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.good.GoodSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface WxGoodSnapShotMapper {

    /**
     * 新增快照
     *
     * @param goodSnapshot
     * @return
     */
    Integer add(GoodSnapshot goodSnapshot);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    GoodSnapshot findById(@Param("id") Long id);
}
