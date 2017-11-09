package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author  by kunlun
 * @created on 2017/10/10.
 */
@Controller
@RequestMapping("behavior")
public class BehaviorController {

    /**
     * 访问品类分析
     *
     * @param activity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/visitCategory", method = RequestMethod.GET)
    public BaseResult visitCategory(Activity activity) {
        return null;
    }

    /**
     * 访问时段分析
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/visitTimes", method = RequestMethod.GET)
    public BaseResult visitTimes() {
        return null;
    }

    /**
     * 留存时间分析
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stayTimes", method = RequestMethod.GET)
    public BaseResult stayTimes() {
        return null;
    }

    /**
     * 年龄段/性别分析
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public BaseResult profile() {
        return null;
    }


}
