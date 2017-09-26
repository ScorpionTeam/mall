package com.scoprion.mall.user.controller;

import com.scoprion.mall.domain.User;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created on 2017/9/26.
 */
@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    /**
     * 登录
     *
     * @param map
     * @param user
     * @param result
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public BaseResult loginSubmit(ModelMap map, @Valid User user, BindingResult result, HttpServletRequest request) {

        return null;
    }

    /**
     * 跳转注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    public BaseResult registerSubmit(@Valid User user) {

        return null;
    }

    /**
     * 修改个人资料
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public BaseResult editProfile(User user) {
        return null;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        return "";
    }

    /**
     * 会员详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Long id) {
        return "profile";
    }

}
