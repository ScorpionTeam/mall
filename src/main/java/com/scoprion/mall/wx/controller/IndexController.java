package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.annotation.ApiVersion;
import com.scoprion.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author by kunlun
 * @created on 2017/11/5.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Value("${spring.datasource.url}")
    private String url;
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/test")
    public BaseResult index() {
        LOGGER.info("版本号：V1");
        return BaseResult.success("V1");
    }

//    @Access
//    @GetMapping("/test")
//    @ApiVersion(2)
//    public BaseResult index1() {
//        LOGGER.info("版本号：V2");
//        return BaseResult.success("V2");
//    }

}
