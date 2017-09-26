package com.scoprion.mall.index.controller;

import com.scoprion.annotation.AccessSecret;
import com.scoprion.mall.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/9/25.
 */
@Controller
@RequestMapping("/mall")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @AccessSecret(visit = false)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map) {
        List<String> list = new ArrayList<>();
        list = indexService.findAll();
        map.addAttribute("key", list);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/index/json", method = RequestMethod.GET)
    public String json() {
        return "index";
    }

    @AccessSecret(visit = true)
    @RequestMapping(value = "/four", method = RequestMethod.GET)
    public String error(ModelMap map) {
        return "four";
    }

    @AccessSecret(visit = true)
    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String redis() {
        stringRedisTemplate.opsForValue().set("springboot","springboot");
        return null;
    }

}
