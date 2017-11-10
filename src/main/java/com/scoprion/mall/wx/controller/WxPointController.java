package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.PointLog;
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
@RequestMapping("wx/member")
public class WxPointController {

    @Autowired
    private WxPointService wxPointService;

    /**
     * 个人信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public BaseResult profile(String userId) {
        return wxPointService.findByUserId(userId);
    }

    /**
     * 个人积分
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/personalScore",method = RequestMethod.GET)
    public BaseResult personalScore(Long id, String userId){
        return wxPointService.personalScore(id,userId);
    }


    //TODO  等级划分  抢购拼团

    /**
     * 等级划分
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grade",method = RequestMethod.GET)
    public BaseResult grade(String userId){
        return wxPointService.grade(userId);
    }
}
