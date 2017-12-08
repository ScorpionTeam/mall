package com.scoprion.mall.seller.service;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.seller.mapper.SellerMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author by hmy
 * @created on 2017/12/7/007.
 */
@SuppressWarnings("ALL")
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerMapper sellerMapper;


    /**
     * 商户店铺建立
     *
     * @param seller
     * @return
     * @throws Exception
     */
    @Override
    public BaseResult add(Seller seller) throws Exception {
        String userId = WxUtil.getOpenId(seller.getWxCode());
        if (StringUtils.isEmpty(userId)) {
            return BaseResult.parameterError();
        }
        Integer validByUserResult = sellerMapper.validByUserId(userId);
        if (validByUserResult > 0) {
            return BaseResult.error("ERROR", "不可重复创建店铺");
        }
        if (seller == null) {
            return BaseResult.parameterError();
        }
        Integer validNameResult = sellerMapper.validByName(seller.getSellerName());
        if (validNameResult > 0) {
            return BaseResult.error("ERROR", "店铺名称已存在");
        }
        Integer result = sellerMapper.add(seller);
        if (result <= 0) {
            return BaseResult.error("ERROR", "新增店铺失败");
        }
        return BaseResult.success("新增店铺成功");
    }

    /**
     * 删除店铺
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult delete(Long id) {
        if (StringUtils.isEmpty(id.toString())) {
            return BaseResult.parameterError();
        }
        int result = sellerMapper.delete(id);
        if (result <= 0) {
            return BaseResult.error("ERROR", "删除失败");
        }
        return BaseResult.success("删除成功");
    }

    /**
     * 修改店铺信息
     *
     * @param seller
     * @return
     */
    @Override
    public BaseResult modify(Seller seller) {
        int result = sellerMapper.modify(seller);
        if (result <= 0) {
            return BaseResult.error("ERROR", "修改失败");
        }
        return BaseResult.success("修改成功");
    }

    /**
     * 注册
     *
     * @param mallUser
     * @return
     * @throws Exception
     */
    @Override
    public BaseResult register(MallUser mallUser) throws Exception {
        if (mallUser == null) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(mallUser.getMobile())) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(mallUser.getPassword())) {
            return BaseResult.parameterError();
        }
        Integer nickNameResult = sellerMapper.validByNickName(mallUser.getNickName());
        if (nickNameResult > 0) {
            return BaseResult.error("ERROR", "该昵称已存在");
        }
        if (mallUser.getPassword().length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("ERROR", "密码长度不能小于六位");
        }
        if (mallUser.getMobile().length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("ERROR", "手机号码有误");
        }
        Integer mobileResult = sellerMapper.validByMobile(mallUser.getMobile());
        if (mobileResult > 0) {
            return BaseResult.error("ERROR", "手机号已存在");
        }
        String password = mallUser.getPassword();
        String encryptPassword = EncryptUtil.encryptMD5(password);
        mallUser.setPassword(encryptPassword);
        Integer result = sellerMapper.register(mallUser);
        if (result <= 0) {
            return BaseResult.error("ERROR", "注册失败");
        }
        //将用户手机号码作为加密字符串回传
        String tokenStr = EncryptUtil.aesEncrypt(mallUser.getMobile(), "ScorpionMall8888");
        mallUser.setToken(tokenStr);
        return BaseResult.success(tokenStr);
    }


    /**
     * 修改个人信息
     *
     * @param mallUser
     * @return
     */
    @Override
    public BaseResult alter(MallUser mallUser) {
        Integer result = sellerMapper.validByNickName(mallUser.getNickName());
        if (result > 0) {
            return BaseResult.error("ERROR", "该昵称已存在");
        }
        Integer alterResult = sellerMapper.alter(mallUser);
        if (alterResult <= 0) {
            return BaseResult.error("ERROR", "修改失败");
        }
        return BaseResult.success("修改成功");
    }

    /**
     * 微信商户登录
     *
     * @param mallUser
     * @param ip
     * @return
     */
    @Override
    public BaseResult login(MallUser mallUser, String ip) throws Exception {
        if (StringUtils.isEmpty(mallUser.getMobile()) && StringUtils.isEmpty(mallUser.getEmail())) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(mallUser.getPassword())) {
            return BaseResult.parameterError();
        }
        if (mallUser.getEmail().matches("EMAIL_FORMAT") && mallUser.getMobile().length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("ERROR", "输入的邮箱格式或手机号码不正确");
        }
        /*if (mallUser.getMobile().length() < Constant.MOBILE_LENGTH) {
            return BaseResult.error("ERROR", "输入的手机号码小于十一位");
        }*/
        if (mallUser.getPassword().length() < Constant.PASSWORD_MIN_LENGTH) {
            return BaseResult.error("ERROR", "输入的密码小于六位");
        }
        String encryptPassword = EncryptUtil.encryptMD5(mallUser.getPassword());

        //登录
        MallUser user = sellerMapper.login(mallUser.getMobile(), mallUser.getEmail(), encryptPassword);
        if (user == null) {
            return BaseResult.error("登录失败", "输入的账号或邮箱或密码错误");
        }
        //更新商户最后登录ip地址
        sellerMapper.updateLoginIpAddress(mallUser.getId(), ip);
        //将用户手机号作为加密字符回传
        String tokenStr = EncryptUtil.aesEncrypt(mallUser.getMobile(), "ScoprionMall8888");
        mallUser.setToken(tokenStr);
        return BaseResult.success(mallUser);

    }
}

