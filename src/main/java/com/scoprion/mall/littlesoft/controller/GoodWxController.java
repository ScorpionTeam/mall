package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.good.GoodWxService;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@RestController
@RequestMapping("wx/good")
public class GoodWxController {
    @Autowired
    private GoodWxService goodWxService;

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public BaseResult goodDetail(Long goodId){
        return  goodWxService.goodDetail(goodId);
    }
}
