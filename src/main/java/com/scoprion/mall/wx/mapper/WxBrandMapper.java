package com.scoprion.mall.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Brand;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface WxBrandMapper {

    /**
     * 查询品牌
     *
     * @return
     */
    Page<Brand> listByPage();
}
