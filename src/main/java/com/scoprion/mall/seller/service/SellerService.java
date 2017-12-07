package com.scoprion.mall.seller.service;

import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/12/7.
 */
public interface SellerService {

    BaseResult registry(Seller seller);
}
