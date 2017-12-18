package com.scoprion.mall.seller.service.ticket;

import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketExt;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * @author by fk
 * @created on 2017/12/13.
 */
public interface SellerTicketService {

    /**
     * 优惠卷列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerId
     * @param searchKey
     * @return
     */
    PageResult findAll(int pageNo, int pageSize, Long sellerId,String searchKey);

    /**
     * 商家创建优惠券
     *
     * @param ticket
     * @return
     * @throws Exception
     */
    BaseResult add(TicketExt ticket) throws Exception;


    /**
     * 编辑优惠券
     *
     * @param ticket
     * @return
     */
    BaseResult modify(Ticket ticket);

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    BaseResult deleteById(Long id);

    /**
     * 根据主键查询优惠券详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    BaseResult delete(List<Long> idList);
}
