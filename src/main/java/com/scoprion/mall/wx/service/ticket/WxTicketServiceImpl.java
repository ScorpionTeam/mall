package com.scoprion.mall.wx.service.ticket;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketUser;
import com.scoprion.mall.wx.mapper.WxTicketMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Service
public class WxTicketServiceImpl implements WxTicketService {

    @Autowired
    private WxTicketMapper wxTicketMapper;


    /**
     * 用户优惠券列表
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public PageResult findByUserId(Integer pageNo, Integer pageSize,Long userId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Ticket> page = wxTicketMapper.findByUserId(userId);
        if(page == null) {
            return new PageResult(new ArrayList<Page>());
        }
        return new PageResult(page);
    }

    /**
     * 判断优惠卷使用时间(useDate)
     * @param userId
     * @param ticketId
     * @return
     */
    @Override
    public BaseResult findByTicketId(Long userId, Long ticketId) {
        TicketUser useDate = wxTicketMapper.findByTicketId(userId, ticketId);
        Date date = useDate.getUseDate();
        //当前时间
        Date currentDate = new Date();
        if(date.getTime() > currentDate.getTime()) {
            return BaseResult.error("date_out", "还未到优惠卷的使用时间");
        }
        return BaseResult.success("可以使用优惠卷");
    }


    /**
     *
     * @param ticketId
     * @param userId
     * @return
     */
    @Override
    public BaseResult addTicket(Long ticketId, Long userId) {
        TicketUser ticketUser=wxTicketMapper.detail(ticketId,userId);
        Long ticketId1=ticketUser.getTicketId();
        Long userId1=ticketUser.getUserId();
        if(ticketId1==null||userId1==null){
            Integer result=wxTicketMapper.addTicket(ticketId,userId);
            if (result>0){
                return BaseResult.success("领取成功");
            }
            return BaseResult.success("领取失败");
        }
        return BaseResult.success("每种优惠券只可领取一张");
    }


}
