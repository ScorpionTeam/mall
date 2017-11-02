package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.brand.BrandWxService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@RestController
@RequestMapping("wx/brand")
public class BrandWxController {
    @Autowired
    private BrandWxService brandWxService;

    @RequestMapping(value = "/listByPage",method = RequestMethod.GET)
    public PageResult listByPage(Integer pageNo,Integer pageSize){
        return brandWxService.listByPage(pageNo,pageSize);
    }
}
