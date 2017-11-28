package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Brand;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <品牌>
 * @date 2017-11-08 15:49
 */
@Mapper
public interface BrandMapper {
    /**
     * 增加品牌
     *
     * @param brand Brand
     * @return
     */
    int add(Brand brand);

    /**
     * 校验名称是否存在
     *
     * @param brandName
     * @return
     */
    int validByName(@Param("brandName") String brandName);

    /**
     * 根据id 名称校验名称是否存在
     *
     * @param id
     * @param brandName
     * @return
     */
    int validByNameAndId(@Param("id") Long id, @Param("brandName") String brandName);

    /**
     * 修改品牌
     *
     * @param brand Brand
     * @return
     */
    int modify(Brand brand);

    /**
     * 批量删除品牌
     *
     * @param idList List<Long>
     * @return
     */
    int batchDelete(@Param("idList") List<Long> idList);

    /**
     * 根据ID查询详情
     *
     * @param id Long
     * @return
     */
    Brand findById(@Param("id") Long id);


    /**
     * 列表查询
     *
     * @param searchKey 模糊查询信息
     * @return
     */
    Page<Brand> findByCondition(@Param("searchKey") String searchKey);

    /**
     * 批量修改品牌状态
     *
     * @param status 状态 1入驻 0 退出 状态 ENTER入驻 QUITE退出
     * @param idList id集合
     * @return
     */
    int batchModifyStatus(@Param("status") String status, @Param("idList") List<Long> idList);
}
