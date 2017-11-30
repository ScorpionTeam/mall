package com.scoprion.mall.wx.service.ticket;

import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;


/**
 * @author by hmy
 * @created on 2017/11/2/002.
 */
public interface WxTicketService {

    /**
     * 用户优惠券列表
     *
     * @param pageNo
     * @param pageSize
     * @param wx_code
     * @return
     */
    PageResult findByUserId(Integer pageNo, Integer pageSize, String  wx_code);

    /**
     * 领取优惠券
     *
     * @param ticketId
     * @param wx_code
     * @return
     */
    BaseResult getTicket(Long ticketId, String wx_code);

    /**
     * 查询所有优惠券列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findAll(Integer pageNo,Integer pageSize);
}
