package com.scoprion.mall.backstage.service.role;

import com.scoprion.mall.domain.SysRole;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:37
 */
public interface RoleService {

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    BaseResult add(SysRole sysRole);

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    BaseResult modify(SysRole sysRole);

    /**
     * 列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey);

    /**
     * 根据id查询角色详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    BaseResult deleteById(Long id);

    /**
     * 角色绑定菜单
     *
     * @param menusId
     * @param roleId
     * @return
     */
    BaseResult bindMenu(Long roleId, List<Long> menusId);

    /**
     * 角色绑定用户
     *
     * @param userId
     * @param roleId
     * @return
     */
    BaseResult bindUser(Long userId, Long roleId);
}
