package com.scoprion.mall.service.banner;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.mapper.BannerMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Service
public class BannerServiceImpl implements BannerService {


    @Autowired
    private BannerMapper bannerMapper;


    /**
     * 创建banner
     *
     * @param banner
     * @return
     */
    @Override
    public BaseResult add(Banner banner) {
        int validName = bannerMapper.validByName(banner.getName());
        if (validName != 0) {
            return BaseResult.error("add_fail", "名称不能重复");
        }
        bannerMapper.add(banner);
        return BaseResult.success("创建成功");
    }

    /**
     * 编辑banner
     *
     * @param banner
     * @return
     */
    @Override
    public BaseResult edit(Banner banner) {
        int validResult = bannerMapper.validByNameAndId(banner.getId(), banner.getName());
        if (validResult != 0) {
            return BaseResult.error("edit_fail", "名称已存在");
        }
        bannerMapper.edit(banner);
        return BaseResult.success("编辑成功");
    }

    /**
     * 根据主键删除banner
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        bannerMapper.deleteByPrimaryKey(id);
        return BaseResult.success("删除成功");
    }

    /**
     * 分页查询Banner
     *
     * @param pageNo
     * @param pageSize
     * @param bannerName
     * @return
     */
    @Override
    public PageResult listByPage(int pageNo, int pageSize, String bannerName) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(bannerName)) {
            bannerName = "%" + bannerName + "%";
        }
        Page<Banner> page = bannerMapper.listByPage(bannerName);
        return new PageResult(page);
    }

    /**
     * 首页展示banner
     *
     * @return
     */
    @Override
    public BaseResult homeShow() {
        return BaseResult.success(bannerMapper.homeShow());
    }
}
