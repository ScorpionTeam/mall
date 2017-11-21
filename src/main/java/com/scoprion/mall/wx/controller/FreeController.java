package com.scoprion.mall.wx.controller;

import com.scoprion.mall.wx.service.free.FreeService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 试用接口
 *
 * @author by kunlun
 * @created on 2017/11/20.
 */
@RestController
@RequestMapping("free")
public class FreeController {

    @Autowired
    private FreeService freeService;

    /**
     * 试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResult findAll(int pageNo, int pageSize) {

        return null;
    }

    /**
     * 参加试用
     *
     * @param activityGoodId
     * @return
     */
    @RequestMapping(value = "/ally", method = RequestMethod.POST)
    public BaseResult apply(Long activityGoodId,String wxCode) {
        return freeService.apply(activityGoodId,wxCode);
    }
}
