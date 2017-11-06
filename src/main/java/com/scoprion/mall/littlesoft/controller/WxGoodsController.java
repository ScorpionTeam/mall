package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.goods.WxGoodsService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {


    @Autowired
    private WxGoodsService wxGoodsService;

    /**
     * 商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/listByPage",method = RequestMethod.GET)
    public PageResult findAll(Integer pageNo,Integer pageSize){
        return wxGoodsService.findAll(pageNo,pageSize);
    }

    /**
     * 根据商品id获取商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public BaseResult goodsDetail(Long id){
        return wxGoodsService.goodsDetail(id);
    }

}
