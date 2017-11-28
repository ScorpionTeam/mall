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
    BaseResult findByUserId(String userId);

    /**
     * 根菜单列表
     *
     * @return
     */
    BaseResult findRootMenu();

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    BaseResult modifyStatus(Long id, String status);
}
