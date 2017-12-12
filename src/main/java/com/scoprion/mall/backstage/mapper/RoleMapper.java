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

    /**
     * 新增
     *
     * @param sysRole
     * @return
     */
    Integer add(SysRole sysRole);

    /**
     * 修改
     *
     * @param sysRole
     * @return
     */
    Integer modify(SysRole sysRole);


    /**
     * 校验角色绑定用户数量
     *
     * @param roleId
     * @return
     */
    Integer validUserByRoleId(@Param("roleId") Long roleId);


    /**
     * 名称校验
     *
     * @param name
     * @return
     */
    Integer validByName(@Param("name") String name);

    /**
     * 名称  id校验
     *
     * @param name
     * @param id
     * @return
     */
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

    /**
     * 校验角色绑定关系
     *
     * @param userId
     * @return
     */
    Integer validRoleRelation(@Param("userId") Long userId);

    /**
     * 更新用户角色关系
     *
     * @param userId
     * @param roleId
     * @return
     */
    Integer updateRoleRelation(@Param("userId") Long userId,
                               @Param("roleId") Long roleId);

    /**
     * 新增用户角色关系
     *
     * @param userId
     * @param roleId
     * @return
     */
    Integer addRoleRelation(@Param("userId") Long userId,
                            @Param("roleId") Long roleId);

    /**
     * 根据菜单id查父菜单id
     *
     * @param menuId
     * @return
     */
    Long findPidByMenuId(@Param("menuId") Long menuId);

    /**
     * 查询角色id 父id记录
     *
     * @param roleId
     * @param parentId
     * @return
     */
    Integer queryExistByPid(@Param("roleId") Long roleId,
                            @Param("parentId") Long parentId);

    /**
     * 加入二级菜单绑定关系
     *
     * @param roleId
     * @param menuId
     * @return
     */
    Integer addRoleMenuRelation(@Param("roleId") Long roleId,
                                @Param("menuId") Long menuId);

    /**
     * 加入一级菜单绑定关系
     *
     * @param roleId
     * @param parentId
     * @return
     */
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

    /**
     * 查询商户角色
     *
     * @return
     */
    SysRole findSellerRole();

}
