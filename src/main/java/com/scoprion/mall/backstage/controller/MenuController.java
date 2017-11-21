package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.menu.MenuService;
import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
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

    @ApiOperation("初始化菜单")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public BaseResult init(String userId) {
        return menuService.init(userId);
    }

    @ApiOperation("菜单列表")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public BaseResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        return menuService.findByCondition(pageNo, pageSize, searchKey);
    }

    @ApiOperation("菜单添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@Validated @RequestBody SysMenu sysMenu) {
        return menuService.add(sysMenu);
    }

    @ApiOperation("菜单修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@Validated @RequestBody SysMenu sysMenu) {
        return menuService.modify(sysMenu);
    }

    @ApiOperation("菜单详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return menuService.findById(id);
    }

    @ApiOperation("菜单删除")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return menuService.deleteById(id);
    }
}
