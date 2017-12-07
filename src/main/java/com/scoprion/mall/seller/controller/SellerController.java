package com.scoprion.mall.seller.controller;

import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/12/7.
 */

@RequestMapping("/seller")
@RestController
public class SellerController {

    /**
     * 商户注册
     *
     * @param seller
     * @return
     */
    @PostMapping("/registry")
    public BaseResult registry(@RequestBody Seller seller) {

        return null;
    }




}
