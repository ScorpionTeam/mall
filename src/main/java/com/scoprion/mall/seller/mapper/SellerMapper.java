package com.scoprion.mall.seller.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.domain.order.OrderExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by kunlun
 * @created on 2017/12/7.
 */

@Mapper
public interface SellerMapper {

    /**
     * 创建商铺
     *
     * @param seller
     * @return
     */
    Integer add(Seller seller);

    /**
     * 修改店铺状态
     *
     * @param id
     * @param status
     * @return
     */
    Integer updateStatus(@Param("status") String status, @Param("id") Long id);

    /**
     * 修改商铺信息
     *
     * @param seller
     * @return
     */
    Integer modify(Seller seller);

    /**
     * 校验名字是否存在
     *
     * @param sellerName
     * @return
     */
    Integer validByName(@Param("sellerName") String sellerName);

    /**
     * 校验该商户是否已经存在商铺
     *
     * @param userId
     * @return
     */
    Integer validByUserId(@Param("userId") Long userId);

    /**
     * 微信商户登录
     *
     * @param mobile
     * @param password
     * @return
     */
    MallUser login(@Param("email") String email, @Param("mobile") String mobile, @Param("password") String password);

    /**
     * 更新商品最后登录的ip地址
     *
     * @param id
     * @param ip
     */
    void updateLoginIpAddress(@Param("id") Long id, @Param("ip") String ip);

    /**
     * 校验号码是否存在
     *
     * @param mobile
     * @return
     */
    Integer validByMobile(@Param("mobile") String mobile);

    /**
     * 商户注册
     *
     * @param mallUser
     * @return
     */
    Integer register(MallUser mallUser);

    /**
     * 校验昵称
     *
     * @param nickName
     * @return
     */
    Integer validByNickName(@Param("nickName") String nickName);

    /**
     * 商户修改个人信息
     *
     * @param mallUser
     * @return
     */
    Integer alter(MallUser mallUser);


    /**
     * 详情
     *
     * @param userId
     * @return
     */
    Seller findByUserId(@Param("userId") Long userId);

    /**
     * 校验是否已经实名认证
     *
     * @param userId
     * @return
     */
    Integer validCertification(@Param("userId") Long userId);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    Seller findById(@Param("id") Long id);

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    int updateAudit(@Param("id") Long id);

    /**
     * 平台开启商户的店铺
     *
     * @return
     */
    Integer updateStatusAndAudit();

    /**
     * 重新认证
     * @param mallUser
     * @return
     */
    Integer reauth(MallUser mallUser);
}
