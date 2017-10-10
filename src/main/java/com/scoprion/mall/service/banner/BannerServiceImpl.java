package com.scoprion.mall.service.banner;

import com.scoprion.mall.domain.Banner;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Service
public class BannerServiceImpl implements BannerService {


    @Override
    public List<Banner> homeBanner() {
        return null;
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        return null;
    }


}
