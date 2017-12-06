package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.wx.service.category.WxCategoryService;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ycj
 * @version V1.0 <小程序--类目>
 * @date 2017-11-30 09:19
 */
@RestController
@RequestMapping("/wx/category")
public class WxCategoryController {

    @Autowired
    WxCategoryService wxCategoryService;

    /**
     * @param pageNo
     * @param pageSize
     * @param type     PARENT_CATEGORY 一级类目 , CHILD_CATEGORY  子类目 ，不传 全部
     * @return
     */
    @Access
    @ApiOperation("获取类目列表")
    @RequestMapping(value = "findHomePage", method = RequestMethod.GET)
    public PageResult findHomePage(Integer pageNo, Integer pageSize, String type) {
        return wxCategoryService.findHomePage(pageNo, pageSize, type);
    }
}
