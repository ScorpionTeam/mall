package com.scoprion.mall.seller.controller;

import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.seller.service.SellerService;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author by kunlun
 * @created on 2017/12/7.
 */

@RequestMapping("/seller")
@RestController
public class SellerController {


    @Autowired
    private SellerService sellerService;

    /**
     * 商户店铺建立
     *
     * @param seller
     * @return
     */
    @PostMapping("/registry")
    public BaseResult registry(@RequestBody Seller seller) throws Exception{
        return sellerService.register(seller);
    }


    /**
     * 删除店铺
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public BaseResult deleteById(Long id){
        return sellerService.delete(id);
    }


    /**
     * 修改店铺信息
     * @param seller
     * @return
     */
    @PostMapping("/modify")
    public BaseResult modify(@RequestBody Seller seller){
        return sellerService.modify(seller);
    }

}
