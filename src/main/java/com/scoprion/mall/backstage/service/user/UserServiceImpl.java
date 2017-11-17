package com.scoprion.mall.backstage.service.user;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.Member;
import com.scoprion.mall.backstage.mapper.UserMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2017/9/27.
 *
 * @author adming
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 后台登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @param ip       IP地址
     * @return BaseResult
     * @throws Exception e
     */
    @Override
    public BaseResult login(String mobile, String password, String ip) throws Exception {
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return BaseResult.systemError();
        }
        if (mobile.length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("phone_error", "手机号码不正确");
        }
        if (password.length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("password_error", "密码不能小于六位");
        }
        String encryptPassword = EncryptUtil.encryptMD5(password);
        Member member = userMapper.login(mobile, encryptPassword);
        if (null == member) {
            return BaseResult.error("login_fail", "手机号或密码不正确!");
        }
        //更新用户最后登录IP地址
        userMapper.updateLoginIpAddress(member.getId(), ip);
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(member.getMobile(), "ScorpionMall8888");
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + member.getMobile(), member.toString(), 30, TimeUnit.MINUTES);
        return BaseResult.success(tokenStr);
    }

    /**
     * 管理后台注册
     *
     * @param member Member
     * @param ip     注册的IP地址
     * @return BaseResult
     * @throws Exception e
     */
    @Override
    public BaseResult register(Member member, String ip) throws Exception {
        String mobile = member.getMobile();
        String password = member.getPassword();
        String nickName = member.getNickName();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return BaseResult.systemError();
        }
        if (mobile.length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("phone_error", "手机号码不正确");
        }
        if (password.length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("password_error", "密码不能小于六位");
        }
        int mobileCount = userMapper.findByMobile(mobile);
        if (mobileCount > 0) {
            return BaseResult.error("register_fail", "手机已存在");
        }
        int nick = userMapper.findByNickName(nickName);
        if (nick > 0) {
            return BaseResult.error("register_fail", "昵称已存在");
        }
        String encryptPassword = EncryptUtil.encryptMD5(password);
        member.setPassword(encryptPassword);
        int result = userMapper.register(member);
        if (result <= 0) {
            return BaseResult.error("register_fail", "注册失败");
        }
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(mobile, "ScorpionMall8888");
        return BaseResult.success(tokenStr);
    }


    /**
     * 编辑个人信息
     *
     * @param member Member
     * @return BaseResult
     */
    @Override
    public BaseResult modifyUserInfo(Member member) {
        if (member.getId() == null) {
            return BaseResult.error("003", "id不能为空");
        }
        int result = userMapper.modifyUserInfo(member);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("error", "修改失败");
    }


    /**
     * 后台系统退出登录
     *
     * @param mobile 手机号
     * @return BaseResult
     */
    @Override
    public BaseResult logout(String mobile) {
        //从缓存区移除当前账号
        Boolean result = redisTemplate.hasKey("Login:" + mobile);
        if (!result) {
            redisTemplate.delete("Login:" + mobile);
            return BaseResult.success("退出成功");
        }
        return BaseResult.error("exit_error", "没有该账号");
    }

    /**
     * 查询用户列表
     *
     * @param pageNo    当前页
     * @param pageSize  每页条数
     * @param startDate 注册时间
     * @param endDate   注册时间
     * @param searchKey
     * @return PageResult
     */
    @Override
    public PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        if (StringUtils.isEmpty(startDate)) {
            startDate = null;
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = null;
        }
        Page<Member> page = userMapper.findByPage(startDate, endDate, searchKey);
        return new PageResult(page);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Member member = userMapper.findById(id);
        if (member == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(member);
    }

}
