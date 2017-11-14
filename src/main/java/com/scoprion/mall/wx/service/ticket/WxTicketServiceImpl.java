package com.scoprion.mall.wx.service.Ticket;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.wx.mapper.WxTicketMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Service
public class WxTicketServiceImpl implements WxTicketService {

    @Autowired
    private WxTicketMapper wxTicketMapper;


    /**
     * 优惠卷列表
     * @param pageNo
     * @param pageSize
     * @param name 优惠卷名字
     * @return
     */
    @Override
    public PageResult findByTicketList(Integer pageNo, Integer pageSize, String name) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Ticket> page = wxTicketMapper.findByTicketList(pageNo, pageSize, name);
        if(page == null) {
            return new PageResult(new ArrayList<Page>());
        }



        return new PageResult(page);
    }

    /**
     * 优惠卷时间限制
     * @param ticket
     * @return
     */
    @Override
    public BaseResult findByTicketDate(Ticket ticket) {
        //获取当前时间 yyyy-MM-dd HH:mm:ss
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = simpleDateFormat.format(date);

        ticket = wxTicketMapper.findByTicketDate(ticket);
       /* if(ticket.getStartDate().after(dateNowStr) && ticket.getEndDate().before(dateNowStr)) {

        }*/



        return null;
    }

}
