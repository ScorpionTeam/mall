package com.scoprion.mall.good.controller;

import com.scoprion.mall.good.service.GoodService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("good")
public class GoodController {


    @Autowired
    private GoodService goodService;

    /**
     * 查询限时购买商品  分页展示
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/limit-by-time", method = RequestMethod.GET)
    public PageResult findByPageAndLimit(int pageNo, int pageSize) {
        return goodService.findByPageAndLimit(pageNo, pageSize);
    }
}
