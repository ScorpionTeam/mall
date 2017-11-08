package com.scoprion.mall.backstage.service.brand;

import com.scoprion.mall.domain.Brand;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <品牌>
 * @date 2017-11-08 15:47
 */
public interface BrandService {

    /**
     * 增加品牌
     *
     * @param brand Brand
     * @return
     */
    BaseResult add(Brand brand);

    /**
     * 修改品牌
     *
     * @param brand Brand
     * @return
     */
    BaseResult modify(Brand brand);

    /**
     * 批量删除品牌
     *
     * @param idList List<Long>
     * @return
     */
    BaseResult batchDelete(List<Long> idList);

    /**
     * 根据ID查询详情
     *
     * @param id Long
     * @return
     */
    BaseResult findById(Long id);


    /**
     * 列表查询
     *
     * @param pageNo    页码
     * @param pageSize  数量
     * @param searchKey 模糊查询信息
     * @return
     */
    PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey);

    /**
     * 批量修改品牌状态
     *
     * @param status 状态 1入驻 0 退出
     * @param idList id集合
     * @return
     */
    BaseResult batchModifyStatus(String status, List<Long> idList);
}
