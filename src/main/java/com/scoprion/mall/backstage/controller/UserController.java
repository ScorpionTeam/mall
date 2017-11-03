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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/9/26.
 *
 * @author adming
 */
@RestController
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
     * 后台登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @param request  请求
     * @return
     */
    @ApiOperation(value = "后台登录")
    @RequestMapping(value = "/backstageLogin", method = RequestMethod.POST)
    public BaseResult backstageLogin(String mobile, String password, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIPAddress(request);
        return userService.backstageLogin(mobile, password, ip);
    }


//    /**
//     * 跳转注册页面
//     *
//     * @return
//     */
//    @ApiIgnore
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String register() {
//        return "register";
//    }

    /**
     * 管理后台注册
     *
     * @param mobile   手机号
     * @param password 密码
     * @param nickName 昵称
     * @param request  HttpServletRequest
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "管理后台注册")
    @RequestMapping(value = "/backstageRegister", method = RequestMethod.POST)
    public BaseResult backstageRegister(String mobile, String password, String nickName, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIPAddress(request);
        return userService.backstageRegister(mobile, password, nickName, ip);
    }

    /**
     * 修改个人资料
     *
     * @param member
     * @return
     */
    @ApiOperation(value = "编辑个人信息")
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public BaseResult modifyUserInfo(Member member) {
        return userService.modifyUserInfo(member);
    }

    /**
     * 后台系统退出登录
     *
     * @param mobile 手机号
     * @return BaseResult
     */
    @ApiOperation(value = "后台系统退出登录")
    @RequestMapping(value = "/backstageLogout", method = RequestMethod.GET)
    public BaseResult backstageLogout(String mobile) {
        return userService.backstageLogout(mobile);
    }

    /**
     * 分页查询会员列表
     *
     * @param pageNo
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param searchKey
     * @return
     */
    @ApiOperation(value = "会员列表(运营平台)")
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String searchKey) {
        return userService.findByPage(pageNo, pageSize, startDate, endDate, searchKey);
    }

}
