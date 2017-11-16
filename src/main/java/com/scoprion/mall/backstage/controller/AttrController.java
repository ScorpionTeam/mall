package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.backstage.service.attr.AttrService;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规格
 *
 * @author by kunlun
 * @created on 2017/11/16.
 */
@RestController
@RequestMapping("attr")
public class AttrController {

    @Autowired
    private AttrService attrService;


    /**
     * 创建规格
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody JSONObject json) {
        return attrService.add(json);
    }


}
