package com.scoprion.mall.wx.service.banner;

import com.scoprion.result.PageResult;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
public interface WxBannerService {

    /**
     * 关键字、分页查询banner图
     * @param pageNo
     * @param pageSize
     * @param bannerName
     * @return
     */
    PageResult listByPage(Integer pageNo,Integer pageSize,String bannerName);
}
