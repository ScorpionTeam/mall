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
     * 优惠卷列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findByTicketList(Integer pageNo, Integer pageSize,Long userId);

    /**
     * 优惠卷时间限制
     * @param ticket
     * @return
     */
    BaseResult findByTicketDate(Ticket ticket);
}
