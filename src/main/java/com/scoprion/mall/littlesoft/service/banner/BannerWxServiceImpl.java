package com.scoprion.mall.littlesoft.service.banner;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.littlesoft.mapper.BannerWxMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
@Service
public class BannerWxServiceImpl implements BannerWxService {

    @Autowired
    private BannerWxMapper bannerWxMapper;


    /**
     * 关键字、分页查询banner图
     *
     * @param pageNo
     * @param pageSize
     * @param bannerName
     * @return
     */
    @Override
    public PageResult listByPage(Integer pageNo, Integer pageSize, String bannerName) {
        PageHelper.startPage(pageNo,pageSize);
        if (!StringUtils.isEmpty(bannerName)) {
            bannerName="%"+bannerName+"%";
        }
        Page<Banner> page = bannerWxMapper.list(bannerName);
        return new PageResult(page);
    }
}
