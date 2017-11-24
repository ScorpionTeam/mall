package com.scoprion.mall.backstage.service.ticket;

import com.scoprion.mall.domain.Ticket;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created on 2017/10/10.
 * @author ycj
 */
public interface TicketService {

    /**
     * 创建优惠券
     *
     * @param ticket
     * @return
     * @throws Exception
     */
    BaseResult add(Ticket ticket) throws Exception;

    /**
     * 分页查询优惠券
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult listByPage(int pageNo, int pageSize, String searchKey);

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
     * 根据主键查询优惠券
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);
    /**
     * 根据主键批量删除优惠券
     *
     * @param idList
     * @return
     */
    BaseResult batchDelete(List<Long> idList);
}
