package com.scoprion.mall.backstage.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.menu.MenuService;
import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ycj
 * @version V1.0 <系统菜单>
 * @date 2017-11-21 15:58
 */
@RestController
@RequestMapping("/backstage/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Access
    @ApiOperation("查询菜单根节点列表")
    @RequestMapping(value = "/findRootMenu", method = RequestMethod.GET)
    public BaseResult findRootMenu() {
        return menuService.findRootMenu();
    }

    @Access
    @ApiOperation("菜单列表")
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public BaseResult findByUserId(String userId) {
        return menuService.findByUserId(userId);
    }

    @Access
    @ApiOperation("菜单列表")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        return menuService.findByCondition(pageNo, pageSize, searchKey);
    }

    @Access
    @ApiOperation("菜单添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody SysMenu sysMenu) {
        return menuService.add(sysMenu);
    }

    @Access
    @ApiOperation("菜单修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody SysMenu sysMenu) {
        return menuService.modify(sysMenu);
    }

    @Access
    @ApiOperation("菜单详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return menuService.findById(id);
    }

    @Access
    @ApiOperation("菜单删除")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return menuService.deleteById(id);
    }

    @Access
    @ApiOperation("菜单停用启用")
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.GET)
    public BaseResult modifyStatus(Long id, String status) {
        return menuService.modifyStatus(id, status);
    }
}
