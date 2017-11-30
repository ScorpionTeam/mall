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

    Integer modify(SysRole sysRole);


    /**
     * 校验角色绑定用户数量
     *
     * @param roleId
     * @return
     */
    Integer validUserByRoleId(@Param("roleId") Long roleId);


    Integer validByName(@Param("name") String name);

    Integer validByNameAndId(@Param("name") String name,
                             @Param("id") Long id);


    /**
     * 根据id查询角色详情
     *
     * @param id
     * @return
     */
    SysRole findById(@Param("id") Long id);

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    Integer deleteById(@Param("id") Long id);

    Integer validRoleRelation(@Param("userId") Long userId);

    Integer updateRoleRelation(@Param("userId") Long userId,
                               @Param("roleId") Long roleId);

    Integer insertRoleRelation(@Param("userId") Long userId,
                               @Param("roleId") Long roleId);

    Long findPidByMenuId(@Param("menuId") Long menuId);

    Integer queryExistByPid(@Param("roleId") Long roleId,
                            @Param("parentId") Long parentId);

    Integer addRoleMenuRelation(@Param("roleId") Long roleId,
                                   @Param("menuId") Long menuId);

    Integer insertPid(@Param("roleId") Long roleId,
                      @Param("parentId") Long parentId);

    /**
     * 清空角色绑定关系
     *
     * @param roleId
     * @return
     */
    Integer clearRelation(@Param("roleId") Long roleId);

    /**
     * 条件查询
     *
     * @param searchKey
     * @return
     */
    Page<SysRole> findByCondition(@Param("searchKey") String searchKey);
}
