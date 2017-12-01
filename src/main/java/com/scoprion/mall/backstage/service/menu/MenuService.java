package com.scoprion.mall.backstage.service.menu;

import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.awt.*;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:09
 */
public interface MenuService {
    /**
     * 增加
     *
     * @param sysMenu
     * @return
     */
    BaseResult add(SysMenu sysMenu);

    /**
     * 条件查询
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey);

    /**
     * 修改
     *
     * @param sysMenu
     * @return
     */
    BaseResult modify(SysMenu sysMenu);

    /**
     * id查询详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 删除停用
     *
     * @param id
     * @return
     */
    BaseResult deleteById(Long id);

    /**
     * 根据userid- 查询列表
     *
     * @param userId
     * @return
     */
    BaseResult findByUserId(Long userId);

    /**
     * 根菜单列表
     *
     * @return
     */
    BaseResult findRootMenu();


    /**
     * 根据角色id查询菜单列表
     *
     * @param roleId
     * @return
     */
    BaseResult findByRoleId(Long roleId);

    /**
     * 查询菜单列表,分层级
     *
     * @return
     */
    BaseResult findAllMenu();
}
