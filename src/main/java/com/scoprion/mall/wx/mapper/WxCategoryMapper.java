package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Category;
import com.scoprion.mall.domain.CategoryExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-30 09:51
 */
@Mapper
public interface WxCategoryMapper {
    /**
     * 查询一级类目列表
     *
     * @return
     */
    Page<CategoryExt> findParentPage();

    /**
     * 查询子类目列表
     *
     * @return
     * @param parentId
     */
    Page<Category> findChildPage(@Param("parentId") Long parentId);
}
