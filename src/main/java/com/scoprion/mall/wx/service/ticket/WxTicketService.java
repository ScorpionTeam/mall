package com.scoprion.mall.wx.service.ticket;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.Date;


/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public interface WxTicketService {

    /**
     * 用户优惠券列表
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageResult findByUserId(Integer pageNo, Integer pageSize,Long userId);

    /**
     * 判断优惠卷使用时间(useDate)
     * @param userId
     * @param ticketId
     * @return
     */
    BaseResult findByTicketId(Long userId, Long ticketId);

}
