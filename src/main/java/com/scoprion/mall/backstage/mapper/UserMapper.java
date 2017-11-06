package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/27.
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
    Member login(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 根据email登录
     *
     * @param email
     * @param password
     * @return
     */
    Member loginByEmail(String email, String password);


    /**
     * 根据手机查询是否存在
     *
     * @param mobile
     * @return
     */
    int findByMobile(@Param("mobile") String mobile);

    /**
     * 根据email查询是否存在
     *
     * @param email
     * @return
     */
    int findByEmail(@Param("email") String email);

    /**
     * 根据昵称查询是否存在
     *
     * @param nickName
     * @return
     */
    int findByNickName(@Param("nickName")String nickName);

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
    int register(Member member);

    /**
     * 查询用户列表
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param searchKey 匹配条件
     * @return Page
     */
    Page<Member> findByPage(@Param("startDate") String startDate,
                            @Param("endDate") String endDate,
                            @Param("searchKey") String searchKey);

    /**
     * 修改个人信息
     *
     * @param member
     * @return
     */
    int modifyUserInfo(Member member);
}
