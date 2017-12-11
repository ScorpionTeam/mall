package com.scoprion.mall.seller.controller;


import com.scoprion.mall.domain.MallUser;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.seller.service.SellerService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
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
     * 注册
     * @param mallUser
     * @return
     * @throws Exception
     */
    @PostMapping("/registry")
    public BaseResult registry(@RequestBody MallUser mallUser)throws Exception{
        return sellerService.register(mallUser);
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


    /**
     * 修改个人信息
     * @param mallUser
     * @return
     */
    @PostMapping("/alter")
    public BaseResult alter(@RequestBody MallUser mallUser){
        return sellerService.alter(mallUser);
    }


    /**
     * 退出登录
     * @param mobile
     * @param email
     * @return
     */
    @GetMapping("/logout")
    public BaseResult logout(String mobile,String email){
        return sellerService.logout(mobile,email);
    }


    /**
     * 商户订单列表
     * @param pageNo
     * @param pageSize
     * @param sellerId
     * @return
     */
    @GetMapping("findBySellerId")
    public PageResult findBySellerId(Integer pageNo,Integer pageSize,Long sellerId){
        return sellerService.findBySellerId(pageNo,pageSize,sellerId);
    }


}