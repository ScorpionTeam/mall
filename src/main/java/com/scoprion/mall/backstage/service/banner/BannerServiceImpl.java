package com.scoprion.mall.backstage.service.banner;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.backstage.mapper.BannerMapper;
import com.scoprion.mall.domain.MallImage;
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

    @Autowired
    private FileOperationMapper fileOperationMapper;

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
            return BaseResult.error("ERROR", "广告名称不能重复");
        }
        bannerMapper.add(banner);
        if (!StringUtils.isEmpty(banner.getImageUrl())) {
            MallImage mallImage = new MallImage();
            mallImage.setUrl(banner.getImageUrl());
            mallImage.setBannerId(banner.getId());
            fileOperationMapper.add(mallImage);
        }
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
        int validResult = bannerMapper.validByNameAndId(banner.getId(), banner.getName());
        if (validResult != 0) {
            return BaseResult.error("ERROR", "广告名称已存在");
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

    /**
     * 批量修改广告状态
     *
     * @param status 0 正常 1 删除 状态  NORMAL, 正常,UN_NORMAL, 非正常
     * @param idList id集合
     * @return
     */
    @Override
    public BaseResult batchModifyStatus(String status, List<Long> idList) {
        if (StringUtils.isEmpty(status) || idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        int result = bannerMapper.batchModifyStatus(status, idList);
        if (result == 0) {
            return BaseResult.error("ERROR", "修改失败");
        }
        return BaseResult.success("修改成功");
    }

    /**
     * banner详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Banner banner = bannerMapper.findById(id);
        if (banner == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(banner);
    }
}
