package com.scoprion.mall.wx.service.Ticket;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.wx.mapper.TicketWxMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Service
public class TicketWxServiceImpl implements TicketWxService {

    @Autowired
    private TicketWxMapper ticketWxMapper;

    /*
    *创建优惠券
     */
    @Override
    public BaseResult addTicket(Ticket ticket) throws Exception{
        int validResult=ticketWxMapper.validByName(ticket.getTicketName());
        if(validResult==1){
            return BaseResult.error("add_fail", "名称已存在");
        }
        Long ticketNo= IDWorker.getFlowIdWorkerInstance().nextId();
        ticket.setTicketNo(ticketNo.toString());
        int result=ticketWxMapper.add(ticket);
        if ( result==1){
            return BaseResult.success("创建优惠券成功");
        }
        return BaseResult.error("add_fail", "创建优惠券失败");
    }

    /*
    *优惠券列表
     */
    @Override
    public PageResult ticketList(Integer pageNo, Integer pageSize, String ticketName){
        PageHelper.startPage(pageNo,pageSize);
        if(StringUtils.isEmpty(ticketName)){
            ticketName="%"+ticketName+"%";
        }
        Page<Ticket> page=ticketWxMapper.ticketList(ticketName);
        return new PageResult(page);
    }

    /**
     * 修改优惠券
     */
    @Override
    public BaseResult alter(Ticket ticket){
        int validResult=ticketWxMapper.validByName(ticket.getTicketName());
        if (validResult==1){
            return BaseResult.error("alter_fail","名称已存在");
        }
        ticketWxMapper.edit(ticket);
        return BaseResult.success("修改成功");
    }


    /**
     * 删除优惠券
     */
    @Override
    public BaseResult deleteTicket(Long id){
        ticketWxMapper.delete(id);
        return BaseResult.success("删除成功");
    }
}
