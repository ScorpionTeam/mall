package com.scoprion.mall.backstage.service.menu;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.MenuMapper;
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
    MenuMapper menuMapper;

    @Override
    public BaseResult add(SysMenu sysMenu) {
        Integer validCount = menuMapper.validByNameAndUrl(sysMenu.getName(), sysMenu.getUrl());
        if (validCount > 0) {
            return BaseResult.error("add_error", "菜单名称或地址已存在");
        }
        menuMapper.add(sysMenu);
        return BaseResult.success("添加成功");
    }

    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (searchKey != null) {
            searchKey = "%" + searchKey + "%";
        }
        PageHelper.startPage(pageNo, pageSize);
        Page<SysMenu> page = menuMapper.findByCondition(searchKey);
        return new PageResult(page);
    }

    @Override
    public BaseResult list(String userId) {
        Integer validCount = menuMapper.validAdmin(userId);
        List<SysMenu> list;
        if (validCount > 0) {
            //管理员用户菜单
            list = menuMapper.findRootMenuList();
            if (list != null && list.size() > 0) {
                list.forEach(menu -> {
                    List<SysMenu> childMenuList = menuMapper.findRootMenuList();
                    menu.setLeaf(childMenuList);
                });
            }
            return BaseResult.success(list);
        } else {
            list = menuMapper.findByUrlAndUserId("0", userId);
            if (list != null && list.size() > 0) {
                list.forEach(menu -> {
                    List<SysMenu> childMenuList = menuMapper.findByUrlAndUserId(menu.getUrl(), userId);
                    menu.setLeaf(childMenuList);
                });
            }
        }
        return BaseResult.success(list);
    }

    @Override
    public BaseResult findRootMenu() {
        return BaseResult.success(menuMapper.findRootMenu());
    }

    @Override
    public BaseResult modify(SysMenu sysMenu) {
        if (sysMenu.getId() == null) {
            return BaseResult.parameterError();
        }
        Integer validCount = menuMapper.validByIdAndNameAndUrl(sysMenu.getId(), sysMenu.getName(), sysMenu.getUrl());
        if (validCount > 0) {
            return BaseResult.error("add_error", "菜单名称或地址已存在");
        }
        Integer result = menuMapper.modify(sysMenu);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("add_error", "修改失败");
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
        SysMenu menu = menuMapper.findById(id);
        if (CommonEnum.NORMAL.getCode().equals(menu.getStatus())) {
            return BaseResult.error("del_error", "菜单正在使用中不能删除");
        }
        Integer result = menuMapper.deleteById(id);
        if (result < 0) {
            return BaseResult.error("del_error", "删除失败");
        }
        return BaseResult.success("删除失败");
    }
}
