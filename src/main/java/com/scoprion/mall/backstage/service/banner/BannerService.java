package com.scoprion.mall.backstage.service.banner;

import com.scoprion.mall.domain.Banner;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
public interface BannerService {

    /**
     * 创建Banner
     *
     * @param banner
     * @return
     */
    BaseResult add(Banner banner);

    /**
     * 编辑Banner
     *
     * @param banner
     * @return
     */
    BaseResult modify(Banner banner);

    /**
     * 根据主键删除Banner
     *
     * @param id
     * @return
     */
    BaseResult deleteById(Long id);

    /**
     * 分页查询Banner
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult listByPage(int pageNo, int pageSize, String searchKey);

    /**
     * 首页展示Banner
     *
     * @return
     */
    BaseResult homeShow();

    /**
     * 批量修改广告状态
     *
     * @param status 0 正常 1 删除
     * @param idList id集合
     * @return
     */
    BaseResult batchModifyStatus(String status, List<Long> idList);

    /**
     * banner详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);
}
