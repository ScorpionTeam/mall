package com.scoprion.mall.wx.controller;

import com.scoprion.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/11/5.
 */
@RestController
@RequestMapping("index")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BaseResult index() {

        LOGGER.info("进入......");
        return BaseResult.success("Hello world");
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public BaseResult test(String world) {
        return BaseResult.success(world);
    }


}
