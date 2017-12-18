package com.scoprion.mall.seller.service.ticket;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketExt;
import com.scoprion.mall.seller.mapper.SellerTicketMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by fk
 * @created on 2017/12/13.
 */
@Service
public class SellerTicketServiceImpl implements SellerTicketService {

    @Autowired
    private SellerTicketMapper sellerTicketMapper;

    /**
     * 优惠卷列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerId
     * @param searchKey
     * @return
     */
    @Override
    public PageResult findAll(int pageNo, int pageSize, Long sellerId,String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (sellerId==null) {
            return new PageResult();
        }
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Ticket> page = sellerTicketMapper.findAll(sellerId,searchKey);
        if (page == null) {
            return new PageResult();
        }
        return new PageResult(page);
    }

    /**
     * 商家创建优惠券
     *
     * @param ticket
     * @return
     * @throws Exception
     */
    @Override
    public BaseResult add(TicketExt ticket) throws Exception {
        if (ticket.getSellerId()==null){
            return BaseResult.parameterError();
        }
        if (ticket.getMoney()<ticket.getReduceMoney()){
            return BaseResult.error("ERROR","满减金额错误");
        }
        //校验名称是否存在
        Integer validName=sellerTicketMapper.validTicketName(ticket.getTicketName());
        if (validName>0){
            return BaseResult.error("ERROR","名称已存在");
        }
        Long ticketNo = IDWorker.getFlowIdWorkerInstance().nextId();
        ticket.setTicketNo(ticketNo.toString());
        Integer result=sellerTicketMapper.add(ticket);
        if (result<=0){
            return BaseResult.error("ERROR","添加失败");
        }
        return BaseResult.success("添加成功");
    }

    /**
     * 修改优惠券
     *
     * @param ticket
     * @return
     */
    @Override
    public BaseResult modify(Ticket ticket) {
        if (ticket.getId() == null) {
            return BaseResult.parameterError();
        }
        int validResult = sellerTicketMapper.validTicketNameAndId( ticket.getTicketName(),ticket.getId());
        if (validResult !=0) {
            return BaseResult.error("ERROR", "优惠券名称已存在");
        }
        int result = sellerTicketMapper.modify(ticket);
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
        int result = sellerTicketMapper.deleteById(id);
        if (result != 0) {
            return BaseResult.success("删除成功");
        }
        return BaseResult.error("ERROR", "删除失败");
    }


    /**
     * 根据主键查询优惠券详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id==null){
            return BaseResult.parameterError();
        }
        Ticket ticket=sellerTicketMapper.findById(id);
        if (ticket==null){
            return BaseResult.notFound();
        }
        return BaseResult.success(ticket);
    }

    /**
     * 删除成功
     *
     * @param idList
     * @return
     */
    @Override
    public BaseResult delete(List<Long> idList) {
        if (idList==null ||idList.size()==0){
            return BaseResult.parameterError();
        }
        Integer result=sellerTicketMapper.delete(idList);
        if (idList.size()>result){
            return BaseResult.error("ERROR","未完全删除");
        }
        return BaseResult.success("删除成功");
    }
}
