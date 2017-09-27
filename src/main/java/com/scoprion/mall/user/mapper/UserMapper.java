package com.scoprion.mall.user.mapper;

import com.scoprion.mall.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/27.
 */
public interface UserMapper {


    /**
     * 根据手机查询用户信息
     *
     * @param mobile
     * @param password
     * @return
     */
    User findByMobile(String mobile, String password);

    /**
     * 根据email查询用户信息
     *
     * @param email
     * @param password
     * @return
     */
    User findByEmail(String email, String password);


}
