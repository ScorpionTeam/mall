package com.scoprion.mall.seller.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param searchKey
     * @return
     */
    Page<Ticket> findAll(@Param("sellerId") Long sellerId,@Param("searchKey") String searchKey);


    /**
     *校验名称
     *
     * @param sellerId
     * @param ticketName
     * @return
     */
    Integer validTicketName( @Param("ticketName") String ticketName);

    /**
     * 新增优惠券
     *
     * @param ticket
     * @return
     */
    Integer add(TicketExt ticket);

    /**
     *修改优惠券
     *
     * @param ticket
     * @return
     */
    int modify(Ticket ticket);

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询优惠券详情
     *
     * @param id
     * @return
     */
    Ticket findById(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    Integer delete(@Param("idList") List<Long> idList);

    /**
     * 校验优惠券名称
     * @param ticketName
     * @param id
     * @return
     */
    Integer validTicketNameAndId(@Param("ticketName") String ticketName,@Param("id") Long id);
}
