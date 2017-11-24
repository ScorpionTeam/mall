package com.scoprion.mall.wx.mapper;

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

    /**
     * 修改优惠券快照
     *
     * @param ticketSnapshot
     * @return
     */
    int update(TicketSnapshot ticketSnapshot);

    /**
     * 修改状态
     *
     * @param status
     * @param id
     * @return
     */
    int modifyStatus(@Param("status") String status, @Param("id") Long id);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    TicketSnapshot findById(@Param("id") Long id);

    /**
     * 查找快照详情
     * @param ticket
     * @return
     */
    TicketSnapshot findByUserIdAndTicketId(@Param("ticket") Long ticket);
}
