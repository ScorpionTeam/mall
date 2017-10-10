package com.scoprion.mall.good.service;

import com.scoprion.mall.domain.Good;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
public interface GoodService {

    /**
     * 首页展示  限时购商品列表
     *
     * @return
     */
    List<Good> findLimit4ByTimeGoods();

    /**
     * 查询限时购买商品   分页展示
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @return
     */
    PageResult findByPageAndLimit(int pageNo, int pageSize);

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    BaseResult add(Good good);


}
