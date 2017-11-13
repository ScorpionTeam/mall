package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.GoodExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by admin1 on 2017/11/1.
 */
@Mapper
public interface GoodListWxMapper {

    /**
     * 活动商品列表
     *
     * @param
     * type 活动类型
     * 0秒杀
     * 1拼团
     * 2优选
     * @return
     */
    Page<GoodExt> getGoodList(@Param("type") String type);


}
