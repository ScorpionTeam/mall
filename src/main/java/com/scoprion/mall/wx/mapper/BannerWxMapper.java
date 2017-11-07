package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
@Mapper
public interface BannerWxMapper {
    /**
     * 查询banner
     * @return
     */
    Page<Banner> list(@Param("bannerName") String bannerName);
}
