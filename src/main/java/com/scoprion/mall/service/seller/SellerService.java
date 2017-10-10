package com.scoprion.mall.service.seller;

import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/10/10.
 */
public interface SellerService {

    /**
     * 创建商户
     * @param seller
     * @return
     */
    BaseResult add(Seller seller) throws Exception;

    /**
     * 根据主键删除商户
     * @param id
     * @return
     */
    BaseResult deleteByPrimaryKey(Long id);

    /**
     * 编辑商户信息
     * @param seller
     * @return
     */
    BaseResult edit(Seller seller);

    /**
     * 分页查询商户信息
     * @param pageNo
     * @param pageSize
     * @param sellerName
     * @return
     */
    PageResult listByPage(int pageNo,int pageSize,String sellerName);

    /**
     * 查询商户详情
     * @param id
     * @return
     */
    BaseResult sellerInfo(Long id);
}
