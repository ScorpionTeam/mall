package com.scoprion.mall.littlesoft.service.Ticket;

import com.scoprion.mall.domain.Ticket;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public interface TicketWxService {

    /*
    *创建优惠券
     */
    BaseResult addTicket(Ticket ticket);

    /*
    *优惠券列表
     */
    PageResult ticketList(Integer pageNo,Integer pageSize,String ticketName);

}
