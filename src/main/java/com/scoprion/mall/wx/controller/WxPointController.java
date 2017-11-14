package com.scoprion.mall.wx.controller;

import com.scoprion.mall.wx.service.point.WxPointService;
import com.scoprion.result.BaseResult;
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
     * @param wxCode
     * @return
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public BaseResult findByUserId(String wxCode) {
        return wxPointService.findByUserId(wxCode);
    }



    /**
     * 等级划分
     * @param userId
     * @return
     */
    @RequestMapping(value = "/updateByGrade",method = RequestMethod.GET)
    public BaseResult updateByGrade(String userId){
        return wxPointService.updateByGrade(userId);
    }

}
