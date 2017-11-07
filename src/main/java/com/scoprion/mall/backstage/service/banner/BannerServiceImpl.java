package com.scoprion.mall.backstage.service.banner;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.backstage.mapper.BannerMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author admin
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
            return BaseResult.error("add_fail", "广告名称不能重复");
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
    public BaseResult modify(Banner banner) {
        int validResult = bannerMapper.validByName(banner.getName());
        if (validResult != 0) {
            return BaseResult.error("edit_fail", "广告名称已存在");
        }
        bannerMapper.modify(banner);
        return BaseResult.success("修改成功");
    }

    /**
     * 根据主键删除banner
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteById(Long id) {
        int result = bannerMapper.deleteById(id);
        if (result > 0) {
            return BaseResult.success("删除成功");
        }
        return BaseResult.success("删除失败");
    }

    /**
     * 分页查询Banner
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Override
    public PageResult listByPage(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Banner> page = bannerMapper.listByPage(searchKey);
        return new PageResult(page);
    }

    /**
     * 首页展示banner
     *
     * @return
     */
    @Override
    public BaseResult homeShow() {
        List<Banner> result = bannerMapper.homeShow();
        return BaseResult.success(result);
    }
}
