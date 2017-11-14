package com.scoprion.mall.wx.service.ticket;

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
     * 用户优惠券列表
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public PageResult findByList(Integer pageNo, Integer pageSize,Long userId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Ticket> page = wxTicketMapper.findByList(userId);
        if(page == null) {
            return new PageResult(new ArrayList<Page>());
        }
        return new PageResult(page);
    }


}
