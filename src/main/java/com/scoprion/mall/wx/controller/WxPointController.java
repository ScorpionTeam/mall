package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.wx.service.point.WxPointService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@RestController
@RequestMapping("wx/point")
public class WxPointController {

    @Autowired
    private WxPointService wxPointService;

    /**
     * 个人信息
     *
     * @param wxCode
     * @return
     */
    @Access
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public BaseResult findByUserId(String wxCode) {
        return wxPointService.findByUserId(wxCode);
    }


    /**
     * 积分记录
     *
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @return
     */
    @Access
    @RequestMapping(value = "/pointLog", method = RequestMethod.GET)
    public PageResult pointLog(Integer pageNo, Integer pageSize, String wxCode) {
        return wxPointService.pointLog(pageNo, pageSize, wxCode);
    }
}
