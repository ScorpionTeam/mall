package com.scoprion.mall.user.controller;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.mall.domain.User;
import com.scoprion.mall.user.service.UserService;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

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
     * 手机号登录
     *
     * @param mobile
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginByMobileSubmit", method = RequestMethod.POST)
    public BaseResult loginByMobileSubmit(String mobile, String password, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return BaseResult.parameterError();
        }
        String ip = IPUtil.getIPAddress(request);
        return userService.loginByMobileSubmit(mobile, password, ip);
    }


    /**
     * 邮箱登录
     *
     * @param email
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginByEmailSubmit", method = RequestMethod.POST)
    public BaseResult loginByEmailSubmit(String email, String password, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return BaseResult.parameterError();
        }
        String ip = IPUtil.getIPAddress(request);
        return userService.loginByEmailSubmit(email, password, ip);
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
    public BaseResult registerSubmit(@Valid User user) throws Exception {
        return userService.registerSubmit(user);
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
