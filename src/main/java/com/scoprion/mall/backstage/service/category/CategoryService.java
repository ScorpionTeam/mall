package com.scoprion.mall.backstage.service.category;

import com.scoprion.mall.domain.Category;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-29 10:53
 */
@Service
public interface CategoryService {

    /**
     * 增加类目
     *
     * @param brand Brand
     * @return
     */
    BaseResult add(Category brand);

    /**
     * 修改类目
     *
     * @param brand Category
     * @return
     */
    BaseResult modify(Category brand);

    /**
     * 删除类目
     *
     * @param id Long
     * @return
     */
    BaseResult deleteById(Long id);

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
     * @param type      PARENT CHILD
     * @return
     */
    PageResult findByCondition(Integer pageNo, Integer pageSize, String type, String searchKey);

    /**
     * 批量修改类目状态
     *
     * @param status 状态 NORMAL UN_NORMAL
     * @param id     id
     * @return
     */
    BaseResult modifyStatus(String status, Long id);

    /**
     * 商品解绑定类目
     *
     * @param goodIdList 商品 id
     * @return
     */
    BaseResult unbindCategoryGood(List<Long> goodIdList);

    /**
     * 商品绑定类目
     *
     * @param categoryId 类目 id
     * @param goodIdList 商品 id
     * @return
     */
    BaseResult bindCategoryGood(Long categoryId, List<Long> goodIdList);
}
