package com.scoprion.mall.wx.service.banner;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.wx.mapper.WxBannerMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
@Service
public class WxBannerServiceImpl implements WxBannerService {

    @Autowired
    private WxBannerMapper wxBannerMapper;


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
        Page<Banner> page = wxBannerMapper.list(bannerName);
        return new PageResult(page);
    }

//    public static void main(String[] args) {
//        String str=""
//    }
}
