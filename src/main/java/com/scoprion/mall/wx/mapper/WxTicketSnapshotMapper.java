package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @date 2017/11/15.
 */

@Mapper
public interface WxTicketSnapshotMapper {

    /**
     * 新增优惠券快照
     *
     * @param ticketSnapshot
     * @return
     */
    int add(TicketSnapshot ticketSnapshot);


}
