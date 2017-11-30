package com.scoprion.mall.backstage.service.ticket;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.backstage.mapper.TicketMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/10.
 *
 * @author ycj
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public BaseResult add(Ticket ticket) throws Exception {
        if (ticket.getMoney() < ticket.getReduceMoney()) {
            return BaseResult.error("ERROR", "满减金额有误");
        }
        int validResult = ticketMapper.validByName(ticket.getTicketName());
        if (validResult != 0) {
            return BaseResult.error("ERROR", "名称已存在");
        }
        Long ticketNo = IDWorker.getFlowIdWorkerInstance().nextId();
        ticket.setTicketNo(ticketNo.toString());
        int result = ticketMapper.add(ticket);
        if (result != 0) {
            return BaseResult.success("创建优惠券成功");
        }
        return BaseResult.error("ERROR", "创建优惠券失败");
    }

    /**
     * 分页查询优惠券
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey String 模糊查询信息
     * @return
     */
    @Override
    public PageResult listByPage(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Ticket> page = ticketMapper.listPage(searchKey);
        page.forEach(ticket -> {
            if (ticket.getEndDate() != null && ticket.getEndDate().before(new Date())) {
                //结束时间在今天之前，状态为过期状态
                ticket.setStatus(CommonEnum.EXPIRE.getCode());
            }
        });
        return new PageResult(page);
    }

    /**
     * 编辑优惠券
     *
     * @param ticket
     * @return
     */
    @Override
    public BaseResult modify(Ticket ticket) {
        if (ticket.getId() == null) {
            return BaseResult.parameterError();
        }
        int validResult = ticketMapper.validByNameAndId(ticket.getId(), ticket.getTicketName());
        if (validResult != 0) {
            return BaseResult.error("ERROR", "优惠券名称已存在");
        }
        int result = ticketMapper.modify(ticket);
        if (result != 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("ERROR", "修改失败");
    }

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        int result = ticketMapper.deleteById(id);
        if (result != 0) {
            return BaseResult.success("删除成功");
        }
        return BaseResult.error("ERROR", "删除失败");
    }

    /**
     * 根据主键查询优惠券
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Ticket ticket = ticketMapper.findById(id);
        if (ticket == null) {
            return BaseResult.notFound();
        }
        if (ticket.getEndDate().before(new Date())) {
            //结束时间在今天之前，状态为过期状态
            ticket.setStatus(CommonEnum.EXPIRE.getCode());
            //更新
            ticketMapper.modify(ticket);
        }
        return BaseResult.success(ticket);
    }

    @Override
    public BaseResult batchDelete(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        Integer result = ticketMapper.batchDelete(idList);
        if (idList.size() > result) {
            return BaseResult.success("部分批量删除成功");
        }
        return BaseResult.success("批量删除成功");
    }
}
