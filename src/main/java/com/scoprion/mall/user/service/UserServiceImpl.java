package com.scoprion.mall.user.service;

import com.scoprion.mall.domain.User;
import com.scoprion.mall.user.mapper.UserMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2017/9/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
        User user = userMapper.loginByMobile(mobile, encryptPassword);
        if (null == user) {
            return BaseResult.error("login_fail", "手机号或密码不正确!");
        }
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + user.getMobile(), user, 30, TimeUnit.MINUTES);
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
