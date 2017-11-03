package com.scoprion.mall.backstage.service.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Member;
import com.scoprion.mall.backstage.mapper.UserMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
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
        Member member = userMapper.loginByMobile(mobile, encryptPassword);
        if (null == member) {
            return BaseResult.error("login_fail", "手机号或密码不正确!");
        }
        //更新用户最后登录IP地址
        userMapper.updateLoginIpAddress(member.getId(), ip);
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + member.getMobile(), member.toString(), 30, TimeUnit.MINUTES);
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(member.getMobile(), "ScorpionMall8888");
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
        Member member = userMapper.loginByEmail(email, encryptPassword);
        if (null == member) {
            return BaseResult.error("login_fail", "邮箱或密码不正确");
        }
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + member.getEmail(), member.toString(), 30, TimeUnit.SECONDS);
        //将用户email作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(member.getEmail(), "ScorpionMall8888");
        return BaseResult.success(tokenStr);
    }

    /**
     * 注册
     *
     * @param member
     * @return
     */
    @Override
    public BaseResult registerSubmit(Member member) throws Exception {
        int mobile = userMapper.findByMobile(member.getMobile());
        if (mobile > 0) {
            return BaseResult.error("register_fail", "手机已存在");
        }
        int nick = userMapper.findByNickName(member.getNickName());
        if (nick > 0) {
            return BaseResult.error("register_fail", "昵称已存在");
        }
        String encryptPassword = EncryptUtil.encryptMD5(member.getPassword());
        member.setPassword(encryptPassword);
        int result = userMapper.register(member);
        String tokenStr = EncryptUtil.aesEncrypt(member.getMobile(), "ScorpionMall8888");
        redisTemplate.opsForValue().set("Login:" + member.getMobile(), member.toString(), 30, TimeUnit.SECONDS);
        if (result > 0){
            return BaseResult.success(tokenStr);
        }
        return BaseResult.error("register_fail", "注册失败");
    }


    /**
     * 编辑个人信息
     *
     * @param member
     * @return
     */
    @Override
    public BaseResult editProfile(Member member) {
        int result = userMapper.editProfile(member);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("error", "修改失败");
    }


    /**
     * 退出系统
     */
    @Override
    public void logout() {

    }

    /**
     * @param pageNo    当前页
     * @param pageSize  每页条数
     * @param startDate 注册时间
     * @param endDate   注册时间
     * @param sex       性别
     * @return
     */
    @Override
    public PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String sex) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Member> page = userMapper.findByPage(startDate, endDate, sex);
        return new PageResult(page);
    }

}
