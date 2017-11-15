package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketSnapshot;
import com.scoprion.mall.domain.TicketUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface WxTicketMapper {

    /**
     * 用户优惠券列表
     *
     * @param userId
     * @return
     */
    Page<Ticket> findByUserId(@Param("userId") Long userId);

    /**
     * 获取用户优惠券详情
     * 判断优惠卷使用时间(useDate)
     *
     * @param ticketId
     * @param userId
     * @return
     */
    TicketUser findByTicketIdAndUserId(@Param("ticketId") Long ticketId,
                                       @Param("userId") Long userId);


    /**
     * 新增优惠券-y用户关系记录
     *
     * @param ticketUser
     * @return
     */
    int addTicketUser(TicketUser ticketUser);

    /**
     * 获取优惠券详情
     *
     * @param id
     * @return
     */
    Ticket findById(@Param("id") Long id);


}
