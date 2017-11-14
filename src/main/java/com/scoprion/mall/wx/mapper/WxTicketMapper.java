package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface WxTicketMapper {

    /**
     * 优惠卷列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    Page<Ticket> findByTicketList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("name") String name);


    Ticket findByTicketDate(Ticket ticket);
}
