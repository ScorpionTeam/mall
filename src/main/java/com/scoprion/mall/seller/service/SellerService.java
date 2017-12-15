package com.scoprion.mall.seller.service;

import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Store;
import com.scoprion.result.BaseResult;

/**
 * @author by hmy
 * @created on 2017/12/7/007.
 */
public interface SellerService {

    /**
     * 商铺创建
     *
     * @param store
     * @return
     * @throws Exception
     */
    BaseResult add(Store store);

    /**
     * 删除商铺
     *
     * @param id
     * @param status 店铺状态 NORMAL 正常 ,
     *               CLOSE_LEADER 管理员关闭,
     *               CLOSE 关闭，
     *               DELETE 删除状态
     * @param operator 操作人员
     * @return
     */
    BaseResult updateStatus(Long id, String status,String operator);

    /**
     * 修改店铺信息
     *
     * @param store
     * @return
     */
    BaseResult modify(Store store);

    /**
     * 微信商户登录
     *
     * @param mallUser
     * @param ip
     * @return
     * @throws Exception
     */
    BaseResult login(MallUser mallUser, String ip) throws Exception;

    /**
     * 注册
     *
     * @param mallUser
     * @param ip
     * @return
     * @throws Exception
     */
    BaseResult register(MallUser mallUser, String ip) throws Exception;

    /**
     * 修改商家个人信息
     *
     * @param mallUser
     * @return
     */
    BaseResult alter(MallUser mallUser);


    /**
     * 退出登录
     *
     * @param mobile
     * @param email
     * @return
     */
    BaseResult logout(String mobile, String email);


    /**
     * 查找店铺详情
     *
     * @param userId
     * @return
     */
    BaseResult findByUserId(Long userId);

    /**
     * 重新认证
     *
     * @param mallUser
     * @return
     */
    BaseResult reauth(MallUser mallUser);
}
