package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ycj
 * @version V1.0 <类目>
 * @date 2017-11-08 15:49
 */
@Mapper
public interface CategoryMapper {
    /**
     * 增加类目
     *
     * @param brand Category
     * @return
     */
    Integer add(Category brand);

    /**
     * 校验名称是否存在
     *
     * @param categoryName
     * @return
     */
    Integer validByName(@Param("categoryName") String categoryName);

    /**
     * 根据id 名称校验名称是否存在
     *
     * @param id
     * @param categoryName
     * @return
     */
    Integer validByNameAndId(@Param("id") Long id, @Param("categoryName") String categoryName);

    /**
     * 修改类目
     *
     * @param category Category
     * @return
     */
    Integer modify(Category category);

    /**
     * 删除类目
     *
     * @param id Long
     * @return
     */
    Integer deleteById(@Param("id") Long id);

    /**
     * 根据ID查询详情
     *
     * @param id Long
     * @return
     */
    Category findById(@Param("id") Long id);

    /**
     * 根据一级类目查询子类目列表
     *
     * @param parentId
     * @return
     */
    List<Category> findByParentId(@Param("parentId") Long parentId);

    /**
     * 列表查询
     *
     * @param searchKey 模糊查询信息
     * @param type      PARENT CHILD
     * @return
     */
    Page<Category> findByCondition(@Param("searchKey") String searchKey,
                                   @Param("type") String type);

    /**
     * 修改类目状态
     *
     * @param status 状态 NORMAL UN_NORMAL
     * @param id
     * @return
     */
    Integer modifyStatus(@Param("status") String status, @Param("id") Long id);
}
