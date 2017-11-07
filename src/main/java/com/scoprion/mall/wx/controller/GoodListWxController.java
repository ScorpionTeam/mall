package com.scoprion.mall.littlesoft.controller;

import com.scoprion.mall.littlesoft.service.goodlist.GoodWxService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin1 on 2017/11/1.
 */
@RestController
@RequestMapping("wx/goodList")
public class GoodListWxController {
    @Autowired
    private GoodWxService goodListLittleSoftService;

    @RequestMapping(value = "listByPage",method = RequestMethod.GET)
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
