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


    /**
     * 校验卡券名称是否存在
     * @param ticketName
     * @return
     */
    int validByName(@Param("ticketName") String ticketName);

    /**
     * 优惠券分页
     * @param ticketName
     * @return
     */
    Page<Ticket>ticketList(@Param("ticketName") String ticketName);

    /**
     * 修改优惠券
     */
    int edit(Ticket ticket);


    /**
     * 删除优惠券
     */
    int delete(Long id);
}
