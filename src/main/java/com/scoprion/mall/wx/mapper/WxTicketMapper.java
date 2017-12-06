package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketExt;
import com.scoprion.mall.domain.TicketUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author by hmy
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
    Page<TicketExt> findByUserId(@Param("userId") String userId);

    /**
     * 获取用户优惠券详情
     * 判断优惠卷使用时间(useDate)
     *
     * @param ticketId
     * @param userId
     * @return
     */
    int findByTicketIdAndUserId(@Param("ticketId") Long ticketId,
                                       @Param("userId") String userId);


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

    /**
     * 更新优惠券数量
     * @param id
     * @return
     */
    int updateTicketNum(@Param("id") Long id);


    /**
     * 查询所有优惠券列表
     * @return
     */
    Page<Ticket>findAll();
}
