package com.scoprion.mall.backstage.controller;

import com.scoprion.result.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/10/14.
 */
@RestController
@RequestMapping("log")
public class LogController {


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public BaseResult init() {
        return null;
    }

}
