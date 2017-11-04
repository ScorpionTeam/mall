package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface BrandWxMapper {

    /**
     *查询品牌
     *
     * @return
     */
    Page<Brand> listByPage();
}
