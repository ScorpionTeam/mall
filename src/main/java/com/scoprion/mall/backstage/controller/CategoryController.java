package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.category.CategoryService;
import com.scoprion.mall.domain.Category;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-29 10:51
 */

@RestController
@RequestMapping("/backstage/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 增加类目
     *
     * @param category
     * @return
     */
    @Access
    @ApiOperation("增加类目")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    /**
     * 修改类目
     *
     * @param category Category
     * @return
     */
    @Access
    @ApiOperation("修改类目")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody Category category) {
        return categoryService.modify(category);
    }

    /**
     * 根据ID查询详情
     *
     * @param id Long
     * @return
     */
    @Access
    @ApiOperation("根据ID查询详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return categoryService.findById(id);
    }

    /**
     * 删除类目
     *
     * @param id Long
     * @return
     */
    @Access
    @ApiOperation("根据ID删除类目")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return categoryService.deleteById(id);
    }

    /**
     * 修改类目状态
     * 状态 NORMAL UN_NORMAL
     *
     * @return
     */
    @ApiOperation("修改类目状态")
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    public BaseResult modifyStatus(String status, Long id) {
        return categoryService.modifyStatus(status, id);
    }


    /**
     * 列表查询
     *
     * @param pageNo    页码
     * @param pageSize  数量
     * @param searchKey 模糊查询信息
     * @param type      PARENT CHILD
     * @return
     */
    @Access
    @ApiOperation("列表查询")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String type, String searchKey) {
        return categoryService.findByCondition(pageNo, pageSize, type, searchKey);
    }


    /**
     * 商品绑定类目
     *
     * @param object
     * @return
     */
    @Access
    @ApiOperation("商品绑定类目")
    @RequestMapping(value = "/bindCategoryGood", method = RequestMethod.POST)
    public BaseResult bindCategoryGood(@RequestBody JSONObject object) {
        //类目 id
        Long categoryId = object.getObject("categoryId", Long.class);
        //商品id集合
        List<Long> goodIdList = object.getJSONArray("idList").toJavaList(Long.class);
        return categoryService.bindCategoryGood(categoryId, goodIdList);
    }

    /**
     * 商品批量解绑定类目
     *
     * @param object
     * @return
     */
    @Access
    @ApiOperation("商品批量解绑定类目")
    @RequestMapping(value = "/unbindCategoryGood", method = RequestMethod.POST)
    public BaseResult unbindCategoryGood(@RequestBody JSONObject object) {
        //商品id集合
        List<Long> goodIdList = object.getJSONArray("idList").toJavaList(Long.class);
        return categoryService.unbindCategoryGood(goodIdList);
    }
}
