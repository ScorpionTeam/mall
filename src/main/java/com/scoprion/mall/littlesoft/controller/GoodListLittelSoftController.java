package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.goodlist.GoodListLittleSoftService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin1 on 2017/11/1.
 */
@Controller
@RequestMapping("goodList")
public class GoodListLittelSoftController {
    @Autowired
    private  GoodListLittleSoftService goodListLittleSoftService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    /**
     * 获取商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param type   类型：0 秒杀, 1 拼团, 2精选
     * @return
     */
    public PageResult getGoodList(Integer pageNo, Integer pageSize,String type){
       return  goodListLittleSoftService.getGoodList(pageNo,pageSize,type);
    }
}
