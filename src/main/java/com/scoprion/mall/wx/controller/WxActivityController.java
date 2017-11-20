package com.scoprion.mall.wx.controller;

import com.scoprion.mall.wx.service.activity.WxActivityService;
import com.scoprion.result.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by fk
 *
 * @created on 2017/11/12.
 */
@RestController
@RequestMapping("wx/activity")
public class WxActivityController {

    @Autowired
    private WxActivityService wxActivityService;

    /**
     * 拼团
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findByGroup", method = RequestMethod.GET)
    public PageResult findByGroup(int pageNo, int pageSize) {
        return wxActivityService.findByGroup(pageNo, pageSize);
    }

    /**
     * 秒杀
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/secKill", method = RequestMethod.GET)
    public PageResult secKill(int pageNo, int pageSize) {
        return wxActivityService.secKill(pageNo, pageSize);
    }

    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/preference", method = RequestMethod.GET)
    public PageResult preference(int pageNo, int pageSize) {
        return wxActivityService.preference(pageNo, pageSize);
    }

}
