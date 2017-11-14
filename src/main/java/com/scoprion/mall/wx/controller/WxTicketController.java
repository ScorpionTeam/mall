package com.scoprion.mall.wx.controller;
import com.scoprion.mall.domain.Ticket;
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
     * 优惠卷列表
     * @param pageNo
     * @param pageSize
     * @param name 优惠卷名字
     * @return
     */
    @RequestMapping(value = "/findByTicketList", method = RequestMethod.GET)
    public PageResult findByTicketList(Integer pageNo,Integer pageSize,String name){
        return wxTicketService.findByTicketList(pageNo,pageSize,name);
    }

    /**
     * 优惠卷时间限制
     * @param ticket
     * @return
     */
    @RequestMapping(value = "/findByTicketDate", method = RequestMethod.GET)
    public BaseResult findByTicketDate(Ticket ticket) {

        return wxTicketService.findByTicketDate(ticket);
    }
}

