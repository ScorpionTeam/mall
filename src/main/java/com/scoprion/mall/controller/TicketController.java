package com.scoprion.mall.controller;

import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.service.ticket.TicketService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/10/10.
 */
@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 创建优惠券
     *
     * @param ticket
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Ticket ticket) throws Exception {
        return ticketService.add(ticket);
    }

    /**
     * 优惠券分页
     *
     * @param pageNo
     * @param pageSize
     * @param ticketName
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public PageResult init(int pageNo, int pageSize, String ticketName) {
        return null;
    }

    /**
     * 编辑优惠券
     *
     * @param ticket
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public BaseResult edit(Ticket ticket) {
        return null;
    }

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
    public BaseResult deleteByPrimaryKey(Long id) {
        return null;
    }


}
