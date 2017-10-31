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
    Member loginByMobile(String mobile, String password);

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
    int findByMobile(String mobile);

    /**
     * 根据email查询是否存在
     *
     * @param email
     * @return
     */
    int findByEmail(String email);

    /**
     * 根据昵称查询是否存在
     *
     * @param nickName
     * @return
     */
    int findByNickName(String nickName);

    /**
     * 更新用户登录IP地址
     *
     * @param id
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
     *
     * @param startDate
     * @param endDate
     * @param sex
     * @return
     */
    Page<Member> findByPage(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sex") String sex);

}
