package com.scoprion.mall.controller;

import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/10/10.
 */
@Controller
@RequestMapping("activity")
public class ActivityController {


    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult add(Activity activity){
        return null;
    }
}
