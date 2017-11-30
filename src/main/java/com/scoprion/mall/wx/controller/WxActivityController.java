package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.WxGroupOrder;
import com.scoprion.mall.wx.service.activity.WxActivityService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


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
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public PageResult groupList(int pageNo, int pageSize, String activityType) {
        return wxActivityService.groupList(pageNo, pageSize, activityType);
    }

    /**
     * 参加拼团
     *
     * @param wxGroupOrder
     * @param request
     * @return
     */
    @RequestMapping(value = "/joinGroup", method = RequestMethod.POST)
    public BaseResult joinGroup(@RequestBody WxGroupOrder wxGroupOrder, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return wxActivityService.joinGroup(wxGroupOrder, ipAddress);
    }

}
