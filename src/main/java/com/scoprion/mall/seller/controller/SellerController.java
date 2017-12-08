package com.scoprion.mall.seller.controller;

import com.scoprion.mall.domain.MallUser;
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
    @PostMapping("/add")
    public BaseResult add(@RequestBody Seller seller) throws Exception{
        return sellerService.add(seller);
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


    /**
     * 注册
     * @param mallUser
     * @return
     * @throws Exception
     */
    @PostMapping("/registry")
    public BaseResult registry(@RequestBody MallUser mallUser)throws Exception{
        return sellerService.register(mallUser);
    }


    @PostMapping("/alter")
    public BaseResult alter(@RequestBody MallUser mallUser){
        return sellerService.alter(mallUser);
    }
}
