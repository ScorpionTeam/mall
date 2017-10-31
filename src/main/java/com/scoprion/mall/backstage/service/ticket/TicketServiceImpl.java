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

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public BaseResult add(Ticket ticket) throws Exception {
        int validResult = ticketMapper.validByName(ticket.getTicketName());
        if (validResult != 0) {
            return BaseResult.error("add_fail", "名称已存在");
        }
        Long ticketNo = IDWorker.getFlowIdWorkerInstance().nextId();
        ticket.setTicketNo(ticketNo.toString());
        int result = ticketMapper.add(ticket);
        if (result != 0) {
            return BaseResult.success("创建优惠券成功");
        }
        return BaseResult.error("add_fail", "创建优惠券失败");
    }

    /**
     * 分页查询优惠券
     *
     * @param pageNo
     * @param pageSize
     * @param ticketName
     * @return
     */
    @Override
    public PageResult listByPage(int pageNo, int pageSize, String ticketName) {
        return null;
    }

    /**
     * 编辑优惠券
     *
     * @param ticket
     * @return
     */
    @Override
    public BaseResult edit(Ticket ticket) {
        return null;
    }

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        return null;
    }


}
