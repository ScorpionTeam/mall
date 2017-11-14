package com.scoprion.mall.wx.service.ticket;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;


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


}
