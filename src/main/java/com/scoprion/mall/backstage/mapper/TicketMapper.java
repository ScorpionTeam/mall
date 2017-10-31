package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2017/10/10.
 */
@Mapper
public interface TicketMapper {

    int add(Ticket ticket);

    int validByName(String name);

}
