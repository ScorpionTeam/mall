package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.banner.BannerWxService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by admin1
 * @created on 2017/11/3.
 */
@RestController
@RequestMapping("wx/banner")
public class BannerWxController {

    @Autowired
    private BannerWxService bannerWxService;

    /**
     * 分页查询banner
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listByPage",method = RequestMethod.GET)
    public PageResult listByPage(Integer pageNo,Integer pageSize,String bannerName){

        return  bannerWxService.listByPage(pageNo,pageSize,bannerName);
    }
}
