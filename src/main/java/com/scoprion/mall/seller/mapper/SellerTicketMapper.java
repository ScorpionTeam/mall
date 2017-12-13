package com.scoprion.mall.seller.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by fk
 * @created on 2017/12/13.
 */
@Mapper
public interface SellerTicketMapper {

    /**
     * 优惠卷列表
     *
     * @param sellerId
     * @return
     */
    Page<Ticket> findAll(@Param("sellerId") Long sellerId);
}
