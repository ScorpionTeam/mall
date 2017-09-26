package com.scoprion.mall.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 2017/9/26.
 */
@Controller
@RequestMapping("mall")
public class HomeController {

    /**
     * 首页
     * @param map
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(ModelMap map){
        return "home";
    }


}
