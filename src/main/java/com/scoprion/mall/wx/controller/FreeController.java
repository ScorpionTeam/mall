package com.scoprion.mall.wx.controller;

import com.scoprion.result.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 试用接口
 *
 * @author by kunlun
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("free")
public class FreeController {

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public PageResult findAll(int pageNo,int pageSize){

        return null;
    }
}
