package com.scoprion.mall.wx.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Banner;
import org.apache.ibatis.annotations.Param;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
@Mapper
public interface WxBannerMapper {
    /**
     * 查询banner
     * @return
     */
    Page<Banner> list(@Param("bannerName") String bannerName);
}
