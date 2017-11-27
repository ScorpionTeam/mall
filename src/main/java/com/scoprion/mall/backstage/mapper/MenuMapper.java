package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 17:05
 */
@Mapper
public interface MenuMapper {

    Integer add(SysMenu sysMenu);

    Page<SysMenu> findByCondition(@Param("searchKey") String searchKey);

    Integer modify(SysMenu sysMenu);

    Page<SysMenu> init(@Param("userId") String userId);

    SysMenu findById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer validByNameAndUrl(@Param("name") String name,
                              @Param("url") String url);

    Integer validByIdAndNameAndUrl(@Param("id") Long id,
                                   @Param("name") String name,
                                   @Param("url") String url);

    Integer validAdmin(@Param("userId") String userId);

    List<SysMenu> findRootMenuList();

    List<SysMenu> findByUrlAndUserId(@Param("url") String url, @Param("userId") String userId);
}
