package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.point.PointService;
import com.scoprion.result.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/10/29.
 */
@RestController
@RequestMapping("point")
public class PointController {

    @Autowired
    private PointService pointService;

    @ApiOperation(value = "我的积分")
    @RequestMapping(value = "/my-point", method = RequestMethod.GET)
    public BaseResult myPoint(Long userId) {
        return pointService.myPoint(userId);
    }

}
