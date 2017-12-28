package com.scoprion.mall.backstage.service.menu;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.MenuMapper;
import com.scoprion.mall.backstage.mapper.RoleMapper;
import com.scoprion.mall.backstage.mapper.UserMapper;
import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:15
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseResult add(SysMenu sysMenu) {
        Integer validCount = menuMapper.validByNameAndUrl(sysMenu.getMenuName(), sysMenu.getUrl());
        if (validCount > 0) {
            return BaseResult.error("ERROR", "菜单名称或地址已存在");
        }
        menuMapper.add(sysMenu);
        return BaseResult.success("添加成功");
    }

    @Override
    public BaseResult modify(SysMenu sysMenu) {
        if (sysMenu.getId() == null) {
            return BaseResult.parameterError();
        }
        Integer validCount = menuMapper.validByIdAndNameAndUrl(sysMenu.getId(), sysMenu.getMenuName(), sysMenu.getUrl());
        if (validCount > 0) {
            return BaseResult.error("ERROR", "菜单名称或地址已存在");
        }
        Integer result = menuMapper.modify(sysMenu);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("ERROR", "修改失败");
    }

    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        SysMenu menu = menuMapper.findById(id);
        if (menu == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(menu);
    }

    @Override
    public BaseResult deleteById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        //子菜单
        Integer result = menuMapper.deleteById(id);
        if (result <= 0) {
            return BaseResult.error("ERROR", "删除失败");
        }
        return BaseResult.success("删除成功");
    }

    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        PageHelper.startPage(pageNo, pageSize);
        Page<SysMenu> page = menuMapper.findByCondition(searchKey);
        return new PageResult(page);
    }

    @Override
    public BaseResult findByUserId(Long userId) {
        if (userId == null) {
            return BaseResult.parameterError();
        }
        Integer validCount = userMapper.validAdmin(userId);
        List<SysMenu> list;
        if (validCount > 0) {
            //超级管理员用户菜单
            list = menuMapper.findMenuListByRoleId(null, null, 0);
            list.forEach(menu -> {
                List<SysMenu> childMenuList = menuMapper.findMenuListByRoleId(null, menu.getId(), 1);
                menu.setLeaf(childMenuList);
            });
        } else {
            list = menuMapper.findByParentIdAndUserId(null, userId);
            list.forEach(menu -> {
                List<SysMenu> childMenuList = menuMapper.findByParentIdAndUserId(menu.getId(), userId);
                menu.setLeaf(childMenuList);
            });
        }
        return BaseResult.success(list);
    }

    /**
     * 查询所有根菜单列表
     *
     * @return
     */
    @Override
    public BaseResult findRootMenu() {
        return BaseResult.success(menuMapper.findRootMenuList());
    }


    /**
     * 根据角色id查询菜单列表
     *
     * @param roleId
     * @return
     */
    @Override
    public BaseResult findByRoleId(Long roleId) {
        List<SysMenu> list = menuMapper.findMenuListByRoleId(roleId, null, 0);
        list.forEach(item -> {
            //二级菜单
            List<SysMenu> childMenus = menuMapper.findMenuListByRoleId(roleId, item.getId(), 1);
            item.setLeaf(childMenus);
        });
        return BaseResult.success(list);
    }

    @Override
    public BaseResult findAllMenu() {
        return findByRoleId(null);
    }
}
