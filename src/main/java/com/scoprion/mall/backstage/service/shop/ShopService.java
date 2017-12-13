package com.scoprion.mall.backstage.service.shop;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */
public interface ShopService {

    /**
     * 商铺列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param audit     AUDITING 待审核/审核中    NOT_PASS_AUDIT审核未通过  PASS_AUDIT审核通过
     * @param searchKey
     * @return
     */
    PageResult findPage(Long userId, Integer pageNo, Integer pageSize, String audit, String searchKey);


    /**
     * 店铺审核
     *
     * @param audit
     * @param reason
     * @param id
     * @return
     */
    BaseResult audit(String audit, String reason, Long id);

    /**
     * 根据id查询店铺详情
     *
     * @param id  id
     * @return
     */
    BaseResult findById(Long id);
}
