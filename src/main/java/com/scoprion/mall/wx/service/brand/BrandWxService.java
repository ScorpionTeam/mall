package com.scoprion.mall.littlesoft.service.brand;

import com.scoprion.result.PageResult;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
public interface BrandWxService {

    /**
     * 分页查询商标列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult listByPage(Integer pageNo,Integer pageSize);
}
