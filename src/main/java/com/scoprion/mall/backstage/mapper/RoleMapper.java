package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-27 10:02
 */
@Mapper
public interface RoleMapper {

    Integer add(SysRole sysRole);

    Integer validByName(@Param("name") String name);

    Integer validByNameAndId(@Param("name") String name,
                             @Param("id") Long id);

    Integer modify(SysRole sysRole);

    SysRole findById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer validRoleRelation(@Param("userId") Long userId);

    Integer updateRoleRelation(@Param("userId") Long userId,
                               @Param("roleId") Long roleId);

    Integer insertRoleRelation(@Param("userId") Long userId,
                               @Param("roleId") Long roleId);

    Long queryPidByMenuId(@Param("menuId") Long menuId);

    Integer queryExistByPid(@Param("roleId") Long roleId,
                            @Param("parentId") Long parentId);

    Integer updateRoleMenuRelation(@Param("roleId") Long roleId,
                                   @Param("menuId") Long menuId);

    Integer insertPid(@Param("roleId") Long roleId,
                      @Param("parentId") Long parentId);

    Integer clearRelation(@Param("roleId") Long roleId);

    Page<SysRole> findByCondition(@Param("searchKey") String searchKey);
}
