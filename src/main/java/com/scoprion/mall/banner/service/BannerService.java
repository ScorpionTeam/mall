package com.scoprion.mall.banner.service;

import com.scoprion.mall.domain.Banner;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
public interface BannerService {

    /**
     * 查询首页轮播Banner
     *
     * @return
     */
    List<Banner> homeBanner();

    /**
     * 分页查询Banner列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findByPage(int pageNo, int pageSize);
}
