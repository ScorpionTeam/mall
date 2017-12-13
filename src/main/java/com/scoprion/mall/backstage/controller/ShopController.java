package com.scoprion.mall.backstage.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.shop.ShopService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */
@RequestMapping("backstage/shop")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;


    /**
     * 根据id查询店铺详情
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation("根据id查询店铺详情")
    @GetMapping("/findById/{id}")
    public BaseResult findById(@PathVariable("id") Long id) {
        return shopService.findById(id);
    }


    /**
     * 店铺列表
     *
     * @param pageNo
     * @param pageSize
     * @param audit    审核状态 AUDITING待审核  NOT_PASS_AUDIT审核未通过 PASS_AUDIT审核通过
     * @return
     */
    @Access
    @ApiOperation("店铺列表")
    @GetMapping("/findPage")
    public PageResult findPage(Long userId, Integer pageNo, Integer pageSize,
                               String audit, String searchKey) {
        return shopService.findPage(userId, pageNo, pageSize, audit, searchKey);
    }


    /**
     * 店铺审核
     *
     * @param audit  AUDITING 待审核/审核中    NOT_PASS_AUDIT 审核未通过  PASS_AUDIT 审核通过
     * @param reason
     * @param id
     * @return
     */
    @Access
    @ApiOperation("店铺审核")
    @PostMapping("/audit")
    public BaseResult audit(String audit, Long id, String reason) {
        return shopService.audit(audit, reason, id);
    }
}
