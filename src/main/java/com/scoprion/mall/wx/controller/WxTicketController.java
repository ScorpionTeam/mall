package com.scoprion.mall.wx.controller;

import com.scoprion.mall.wx.service.ticket.WxTicketService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@RestController
@RequestMapping("wx/ticket")
public class WxTicketController {

    @Autowired
    private WxTicketService wxTicketService;

    /**
     * 用户优惠券列表
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public PageResult findByUserId(Integer pageNo, Integer pageSize, Long userId) {
        return wxTicketService.findByUserId(pageNo, pageSize, userId);
    }

    /**
     * * 领取优惠券
     *
     * @param ticketId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/addTicket", method = RequestMethod.GET)
    BaseResult addTicket(Long ticketId, Long userId) {
        return wxTicketService.addTicket(userId, ticketId);
    }

    /**
     * 判断优惠卷使用时间(useDate)
     *
     * @param ticketId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findByTicketId", method = RequestMethod.GET)
    public BaseResult findByTicketId(Long userId, Long ticketId) {
        return wxTicketService.findByTicketId(userId, ticketId);
    }

}

