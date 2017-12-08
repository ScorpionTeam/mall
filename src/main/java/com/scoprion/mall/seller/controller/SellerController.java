package com.scoprion.mall.seller.controller;

import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.seller.service.SellerService;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
     * 微信商户登录
     *
     * @param mallUser
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/login")
    public BaseResult login(@RequestBody MallUser mallUser, HttpServletRequest httpServletRequest) throws Exception {
        String ip = IPUtil.getIPAddress(httpServletRequest);
        return sellerService.login(mallUser, ip);
    }

}
