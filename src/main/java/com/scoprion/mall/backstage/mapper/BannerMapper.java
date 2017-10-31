package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Mapper
public interface BannerMapper {

    /**
     * 创建Banner
     *
     * @param banner
     * @return
     */
    int add(Banner banner);

    /**
     * 校验Banner名称
     *
     * @param bannerName
     * @return
     */
    int validByName(@Param("bannerName") String bannerName);

    /**
     * 根据id 名称校验banner名称是否存在
     *
     * @param id
     * @param name
     * @return
     */
    int validByNameAndId(Long id, String name);

    /**
     * 编辑banner
     *
     * @param banner
     * @return
     */
    int edit(Banner banner);

    /**
     * 根据主键删除banner
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 分页查询Banner
     * @param bannerName
     * @return
     */
    Page<Banner> listByPage(@Param("bannerName") String bannerName);

    /**
     * 首页展示banner
     * @return
     */
    List<Banner> homeShow();
}
