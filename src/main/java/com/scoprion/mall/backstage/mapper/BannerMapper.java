package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author admin1
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
     * @param bannerName
     * @return
     */
    int validByNameAndId(@Param("id") Long id, @Param("bannerName") String bannerName);

    /**
     * 编辑banner
     *
     * @param banner
     * @return
     */
    int modify(Banner banner);

    /**
     * 根据主键删除banner
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 分页查询Banner
     *
     * @param searchKey
     * @return
     */
    Page<Banner> listByPage(@Param("searchKey") String searchKey);

    /**
     * 首页展示banner
     *
     * @return
     */
    List<Banner> homeShow();

    /**
     * 批量修改广告状态
     *
     * @param status 0 正常 1 删除 状态  NORMAL, 正常,UN_NORMAL, 非正常
     * @param idList id集合
     * @return int
     */
    int batchModifyStatus(@Param("status") String status, @Param("idList") List<Long> idList);

    /**
     * banner详情
     *
     * @param id
     * @return
     */
    Banner findById(@Param("id") Long id);
}
