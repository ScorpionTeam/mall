package com.scoprion.mall.backstage.controller;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.mall.domain.Member;
import com.scoprion.mall.backstage.service.user.UserService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/9/26.
 *
 * @author adming
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @ApiIgnore
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
    @ApiOperation(value = "手机号码登录")
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
    @ApiOperation(value = "email登录")
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
    @ApiIgnore
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 注册
     *
     * @param member
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "注册")
    @ResponseBody
    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    public BaseResult registerSubmit(@RequestBody Member member, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIPAddress(request);
        member.setLoginIp(ip);
        return userService.registerSubmit(member);
    }

    /**
     * 修改个人资料
     *
     * @param member
     * @return
     */
    @ApiOperation(value = "编辑个人信息")
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public BaseResult editProfile(Member member) {
        return userService.editProfile(member);
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
    @ApiOperation(value = "会员详情")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Long id) {
        return "profile";
    }

    /**
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/user-list-init", method = RequestMethod.GET)
    public String init(ModelMap map) {
        return "/backstage/b-user";
    }

    /**
     * 分页查询会员列表
     *
     * @param pageNo
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param sex
     * @return
     */
    @ApiOperation(value = "会员列表(运营平台)")
    @ResponseBody
    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String sex) {
        return userService.findByPage(pageNo, pageSize, startDate, endDate, sex);
    }

}
