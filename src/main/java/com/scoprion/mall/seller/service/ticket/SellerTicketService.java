package com.scoprion.mall.seller.service.ticket;

import com.scoprion.result.PageResult;

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
     * @return
     */
    PageResult findAll(int pageNo, int pageSize, Long sellerId);
}
