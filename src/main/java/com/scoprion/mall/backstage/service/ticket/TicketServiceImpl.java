package com.scoprion.mall.backstage.service.ticket;

import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.backstage.mapper.TicketMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/10/10.
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public BaseResult add(Ticket ticket) throws Exception {
        return null;
    }

    @Override
    public PageResult listByPage(int pageNo, int pageSize, String ticketName) {
        return null;
    }

    @Override
    public BaseResult edit(Ticket ticket) {
        return null;
    }

    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        return null;
    }

}
