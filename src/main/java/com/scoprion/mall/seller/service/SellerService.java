package com.scoprion.mall.seller.service;

import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;

/**
 * @author by hmy
 * @created on 2017/12/7/007.
 */
public interface SellerService {

    /**
     * 商铺创建
     * @param seller
     * @return
     * @throws Exception
     */
    BaseResult add(Seller seller) throws Exception;

    /**
     * 删除商铺
     * @param id
     * @return
     */
    BaseResult delete(Long id);

    /**
     * 修改店铺信息
     * @param seller
     * @return
     */
    BaseResult modify(Seller seller);

    /**
     * 注册
     * @param mallUser
     * @return
     * @throws Exception
     */
    BaseResult register(MallUser mallUser) throws Exception;
}
