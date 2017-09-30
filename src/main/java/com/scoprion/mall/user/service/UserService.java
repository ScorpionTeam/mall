package com.scoprion.mall.user.service;

import com.scoprion.mall.domain.User;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/9/27.
 */
public interface UserService {

    /**
     * 手机号登录
     *
     * @param mobile
     * @param password
     * @return
     */
    BaseResult loginByMobileSubmit(String mobile, String password, String ip) throws Exception;

    /**
     * email登录
     *
     * @param email
     * @param password
     * @return
     */
    BaseResult loginByEmailSubmit(String email, String password, String ip) throws Exception;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    BaseResult registerSubmit(User user) throws Exception;

    /**
     * 修改个人资料
     *
     * @param user
     * @return
     */
    BaseResult editProfile(User user);

    void logout();

    /**
     * 分页条件查询 会员列表
     * @param pageNo     当前页
     * @param pageSize   每页条数
     * @param startDate  注册时间
     * @param endDate    注册时间
     * @param sex        性别
     * @return
     */
    PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String sex);
}
