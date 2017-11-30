package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.service.brand.BrandService;
import com.scoprion.mall.domain.Brand;
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
 * @created on 2017/10/10
 * 品牌
 */
@RestController
@RequestMapping("/backstage/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /**
     * 增加品牌
     *
     * @param brand Brand
     * @return
     */
    @Access
    @ApiOperation("增加品牌")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    /**
     * 修改品牌
     *
     * @param brand Brand
     * @return
     */
    @Access
    @ApiOperation("修改品牌")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody Brand brand) {
        return brandService.modify(brand);
    }

    /**
     * 批量删除品牌
     *
     * @param jsonObject
     * @return
     */
    @Access
    @ApiOperation("批量删除品牌")
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public BaseResult batchDelete(@RequestBody JSONObject jsonObject) {
        return BaseResult.error("delete_error", "调用批量修改品牌状态（batchModifyStatus）接口");
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
        return brandService.findById(id);
    }

    /**
     * 批量修改品牌状态
     * 状态 ENTER入驻 QUITE退出
     *
     * @return
     */
    @Access
    @ApiOperation("批量修改品牌状态")
    @RequestMapping(value = "/batchModifyStatus", method = RequestMethod.POST)
    public BaseResult batchModifyStatus(@RequestBody JSONObject jsonObject) {
        if (!jsonObject.containsKey(Constant.STATUS) || !jsonObject.containsKey(Constant.ID_LIST)) {
            return BaseResult.parameterError();
        }
        String status = jsonObject.getString(Constant.STATUS);
        List<Long> idList = jsonObject.getJSONArray(Constant.ID_LIST).toJavaList(Long.class);
        return brandService.batchModifyStatus(status, idList);
    }


    /**
     * 列表查询
     *
     * @param pageNo    页码
     * @param pageSize  数量
     * @param searchKey 模糊查询信息
     * @return
     */
    @Access
    @ApiOperation("列表查询")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        return brandService.findByCondition(pageNo, pageSize, searchKey);
    }
}
