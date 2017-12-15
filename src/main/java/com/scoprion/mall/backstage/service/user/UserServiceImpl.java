package com.scoprion.mall.backstage.service.user;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.backstage.mapper.UserMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            return BaseResult.error("ERROR", "手机号码不正确");
        }
        if (password.length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("ERROR", "密码不能小于六位");
        }
        String encryptPassword = EncryptUtil.encryptMD5(password);
        MallUser mallUser = userMapper.login(mobile, encryptPassword);
        if (null == mallUser) {
            return BaseResult.error("ERROR", "手机号或密码不正确!");
        }
        //更新用户最后登录IP地址
        userMapper.updateLoginIpAddress(mallUser.getId(), ip);
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(mallUser.getMobile(), "ScorpionMall8888");
        mallUser.setToken(tokenStr);
        //设置用户登录有效期为30分钟
        redisTemplate.opsForValue().set("Login:" + mallUser.getMobile(), mallUser.toString(), 30, TimeUnit.MINUTES);
        return BaseResult.success(mallUser);
    }

    /**
     * 管理后台注册
     *
     * @param member MallUser
     * @param ip     注册的IP地址
     * @return BaseResult
     * @throws Exception e
     */
    @Override
    public BaseResult register(MallUser member, String ip) throws Exception {
        String mobile = member.getMobile();
        String password = member.getPassword();
        String nickName = member.getNickName();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return BaseResult.systemError();
        }
        if (mobile.length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("ERROR", "手机号码不正确");
        }
        if (password.length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("ERROR", "密码不能小于六位");
        }
        int mobileCount = userMapper.findByMobile(mobile);
        if (mobileCount > 0) {
            return BaseResult.error("ERROR", "手机已存在");
        }
        int nick = userMapper.findByNickName(nickName);
        if (nick > 0) {
            return BaseResult.error("ERROR", "昵称已存在");
        }
        setAge(member);
        String encryptPassword = EncryptUtil.encryptMD5(password);
        member.setPassword(encryptPassword);
        int result = userMapper.register(member);
        if (result <= 0) {
            return BaseResult.error("ERROR", "注册失败");
        }
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(mobile, "ScorpionMall8888");
        member.setToken(tokenStr);
        return BaseResult.success(member);
    }


    /**
     * 编辑个人信息
     *
     * @param member MallUser
     * @return BaseResult
     */
    @Override
    public BaseResult modifyUserInfo(MallUser member) {
        if (member.getId() == null) {
            return BaseResult.error("003", "id不能为空");
        }
        int result = userMapper.modifyUserInfo(member);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("ERROR", "修改失败");
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
        if (result) {
            redisTemplate.delete("Login:" + mobile);
            return BaseResult.success("退出成功");
        }
        return BaseResult.error("ERROR", "没有该账号");
    }

    /**
     * 查询用户列表
     *
     * @param pageNo    当前页
     * @param pageSize  每页条数
     * @param startDate 注册时间
     * @param endDate   注册时间
     * @param searchKey
     * @param userType  用户类型
     * @param certification  用户认证
     * @return PageResult
     */
    @Override
    public PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate,
                                 String searchKey, String userType,String certification) {
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
        Page<MallUser> page = userMapper.findByPage(startDate, endDate, searchKey, userType,certification);
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
        MallUser member = userMapper.findById(id);
        if (member == null) {
            return BaseResult.notFound();
        }
//        String password = member.getPassword();
//        try {
//            password = EncryptUtil.aesDecrypt(password, Constant.ENCRYPT_KEY);
//            member.setPassword(password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return BaseResult.success(member);
    }

    /**
     * 审核商户信息
     *
     * @param sellerId
     * @param operateId
     * @param certification
     * @param reason
     * @return
     */
    @Override
    public BaseResult auditSeller(Long sellerId, Long operateId, String certification, String reason) {
        if (operateId == null || sellerId == null || StringUtils.isEmpty(certification)) {
            return BaseResult.parameterError();
        }
        int validResult = userMapper.validAdmin(operateId);
        if (validResult == 0) {
            return BaseResult.error("audit_error", "只有管理员用户才能审核");
        }
        if (certification.equals(CommonEnum.NOT_PASS_AUDIT.getCode()) && StringUtils.isEmpty(reason)) {
            return BaseResult.error("audit_error", "请填写未通过原因");
        }
        int result = userMapper.auditSeller(sellerId, certification, reason);
        if (result > 0) {
            return BaseResult.success("审核成功");
        }
        return BaseResult.error("audit_error", " 审核失败");
    }

    /**
     * 计算年龄和性别
     *
     * @param member
     */
    public static void setAge(MallUser member){
        int leg=member.getCertificateId().length();
        if (leg==18){
            int se=Integer.valueOf(member.getCertificateId().substring(leg-1)) % 2;
            String dates=member.getCertificateId().substring(6,10);
            SimpleDateFormat df=new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int age=Integer.parseInt(year)-Integer.parseInt(dates);
            member.setAge(age);
            if (Integer.parseInt(member.getCertificateId().substring(16).substring(0,1))% 2==0){
                member.setSex("FEMALE");
            }
            member.setSex("MALE");
        }else {
            String dates="19"+member.getCertificateId().substring(6,8);
            SimpleDateFormat df=new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int age=Integer.parseInt(year)-Integer.parseInt(dates);
            member.setAge(age);
            if (Integer.parseInt(member.getCertificateId().substring(14,15))% 2==0){
                member.setSex("FEMALE");
            }
            member.setSex("MALE");
        }
    }
}
