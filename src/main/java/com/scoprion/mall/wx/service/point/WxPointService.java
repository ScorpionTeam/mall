package com.scoprion.mall.wx.service.point;


import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;


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
     * 积分记录
     *
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @return
     */
    PageResult pointLog(Integer pageNo, Integer pageSize, String wxCode);
}
