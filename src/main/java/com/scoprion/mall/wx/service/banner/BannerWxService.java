package com.scoprion.mall.littlesoft.service.banner;

import com.scoprion.result.PageResult;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
public interface BannerWxService {

    /**
     * 关键字、分页查询banner图
     * @param pageNo
     * @param pageSize
     * @param bannerName
     * @return
     */
    PageResult listByPage(Integer pageNo,Integer pageSize,String bannerName);
}
