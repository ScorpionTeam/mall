package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.MallUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/27.
 *
 * @author adming
 */
@Mapper
public interface UserMapper {


    /**
     * 根据手机号码登录
     *
     * @param mobile
     * @param password
     * @return
     */
    MallUser login(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 根据email登录
     *
     * @param email
     * @param password
     * @return
     */
    MallUser loginByEmail(String email, String password);


    /**
     * 根据手机查询是否存在
     *
     * @param mobile
     * @return
     */
    int findByMobile(@Param("mobile") String mobile);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    MallUser findById(@Param("id") Long id);

    /**
     * 根据昵称查询是否存在
     *
     * @param nickName
     * @return
     */
    int findByNickName(@Param("nickName") String nickName);

    /**
     * 更新用户登录IP地址
     *
     * @param id
     * @param ip
     * @return
     */
    int updateLoginIpAddress(@Param("id") Long id, @Param("ip") String ip);

    /**
     * 注册
     *
     * @param member
     * @return
     */
    int register(MallUser member);

    /**
     * 查询用户列表
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param searchKey 匹配条件
     * @param userType 用户类型
     * @param certification 用户认证
     * @return Page
     */
    Page<MallUser> findByPage(@Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              @Param("searchKey") String searchKey,
                              @Param("userType") String userType,
                              @Param("certification") String certification);

    /**
     * 修改个人信息
     *
     * @param member
     * @return
     */
    int modifyUserInfo(MallUser member);

    /**
     * 校验是否是管理员用户
     *
     * @param userId
     * @return
     */
    int validAdmin(@Param("userId") Long userId);

    /**
     * 商户信息审核
     *
     * @param sellerId
     * @param certification
     * @param reason
     * @return
     */
    int auditSeller(@Param("sellerId") Long sellerId,
                    @Param("certification") String certification,
                    @Param("reason") String reason);
}
