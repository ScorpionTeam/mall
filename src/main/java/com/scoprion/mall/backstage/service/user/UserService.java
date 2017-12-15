package com.scoprion.mall.backstage.service.user;

import com.scoprion.mall.domain.MallUser;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/9/27.
 *
 * @author adming
 */
public interface UserService {

    /**
     * 后台登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @param ip       IP地址
     * @return
     * @throws Exception
     */
    BaseResult login(String mobile, String password, String ip) throws Exception;

    /**
     * 管理后台注册
     *
     * @param ip     注册的IP地址
     * @param member MallUser
     * @return
     * @throws Exception
     */
    BaseResult register(MallUser member, String ip) throws Exception;

    /**
     * 修改个人资料
     *
     * @param member
     * @return
     */
    BaseResult modifyUserInfo(MallUser member);

    /**
     * 后台系统退出登录
     *
     * @param mobile 手机号
     * @return BaseResult
     */
    BaseResult logout(String mobile);

    /**
     * 分页条件查询 会员列表
     *
     * @param pageNo    当前页
     * @param pageSize  每页条数
     * @param startDate 注册时间
     * @param endDate   注册时间
     * @param sex       性别
     * @param userType   用户类型
     * @param certification   认证
     * @return
     */
    PageResult findByPage(int pageNo, int pageSize, String startDate, String endDate, String sex,String userType,String certification);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 审核商户信息
     *
     * @param sellerId
     * @param operateId
     * @param certification
     * @param reason
     * @return
     */
    BaseResult auditSeller(Long sellerId, Long operateId, String certification, String reason);
}
