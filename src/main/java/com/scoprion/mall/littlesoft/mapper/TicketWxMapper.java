package com.scoprion.mall.littlesoft.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface TicketWxMapper {

    /*
    *创建卡包优惠券
     */
    int add(Ticket ticket);

    int validByName(String name);

    Page<Ticket>ticketList(@Param("ticketName") String ticketName);
}
