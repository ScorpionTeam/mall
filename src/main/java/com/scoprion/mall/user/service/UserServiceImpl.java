package com.scoprion.mall.user.service;

import com.alibaba.druid.util.StringUtils;
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
     * @param ip
     * @return
     */
    @Override
    public BaseResult loginByMobileSubmit(String mobile, String password, String ip) throws Exception {
        String encryptPassword = EncryptUtil.encryptMD5(password);
        User user = userMapper.loginByMobile(mobile, encryptPassword);
        if (null == user) {
            return BaseResult.error("login_fail", "手机号或密码不正确!");
        }
        //更新用户最后登录IP地址
        userMapper.updateLoginIpAddress(user.getId(), ip);
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + user.getMobile(), user.toString(), 30, TimeUnit.MINUTES);
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(user.getMobile(), "ScorpionMall8888");
        return BaseResult.success(tokenStr);
    }

    /**
     * email登录
     *
     * @param email
     * @param password
     * @param ip
     * @return
     */
    @Override
    public BaseResult loginByEmailSubmit(String email, String password, String ip) throws Exception {
        String encryptPassword = EncryptUtil.encryptMD5(password);
        User user = userMapper.loginByEmail(email, encryptPassword);
        if (null == user) {
            return BaseResult.error("login_fail", "邮箱或密码不正确");
        }
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + user.getEmail(), user.toString(), 30, TimeUnit.SECONDS);
        //将用户email作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(user.getEmail(), "ScorpionMall8888");
        return BaseResult.success(tokenStr);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public BaseResult registerSubmit(User user) throws Exception {
        int mobile = userMapper.findByMobile(user.getMobile());
        if (mobile > 0) {
            return BaseResult.error("register_fail", "手机已存在");
        }
        int nick = userMapper.findByNickName(user.getNickName());
        if (nick > 0) {
            return BaseResult.error("register_fail", "昵称已存在");
        }
        String encryptPassword = EncryptUtil.encryptMD5(user.getPassword());
        user.setPassword(encryptPassword);
        int result = userMapper.register(user);
        String tokenStr = EncryptUtil.aesEncrypt(user.getMobile(), "ScorpionMall8888");
        redisTemplate.opsForValue().set("Login:" + user.getMobile(), user.toString(), 30, TimeUnit.SECONDS);
        if (result > 0)
            return BaseResult.success(tokenStr);
        return BaseResult.error("register_fail", "注册失败");
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
