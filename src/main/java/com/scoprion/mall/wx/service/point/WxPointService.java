package com.scoprion.mall.wx.service.point;


import com.scoprion.result.BaseResult;


/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
public interface WxPointService {

    /**
     * 根据用户id查询用户信息
     *
     * @param wxCode
     * @return
     */
    BaseResult findByUserId(String wxCode);


    /**
     * 等级划分
     * @param userId
     * @return
     */
    BaseResult updateByGrade(String userId);

}
