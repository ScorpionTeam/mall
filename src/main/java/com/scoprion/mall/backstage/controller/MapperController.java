package com.scoprion.mall.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by admin1 on 2017/10/11.
 */
@Controller
public class MapperController {
    /**
     * 后台跳转
     */
    @ApiIgnore
    @RequestMapping(value = "/backstage", method = RequestMethod.GET)
    public String backstage() {
        return "backstage/b-home";
    }

    @ApiIgnore
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "backstage/b-user";
    }

    @ApiIgnore
    @RequestMapping(value = "/good", method = RequestMethod.GET)
    public String good() {
        return "backstage/b-good";
    }


}
