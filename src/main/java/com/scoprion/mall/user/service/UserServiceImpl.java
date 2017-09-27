package com.scoprion.mall.user.service;

import com.scoprion.mall.domain.User;
import com.scoprion.mall.user.mapper.UserMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/9/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 手机号码登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public BaseResult loginByMobileSubmit(String mobile, String password) throws Exception {
        String encryptPassword = EncryptUtil.encryptMD5(password);
        User user = userMapper.findByMobile(mobile, encryptPassword);
        if (null == user) {
            return BaseResult.error("login_fail", "手机号或密码不正确!");
        }

        return null;
    }

    /**
     * email登录
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public BaseResult loginByEmailSubmit(String email, String password) {
        return null;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public BaseResult registerSubmit(User user) {
        return null;
    }


    /**
     * 编辑个人信息
     *
     * @param user
     * @return
     */
    @Override
    public BaseResult editProfile(User user) {
        return null;
    }


    /**
     * 退出系统
     */
    @Override
    public void logout() {

    }
}
