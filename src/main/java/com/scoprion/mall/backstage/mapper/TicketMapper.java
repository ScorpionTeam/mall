package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.result.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2017/10/10.
 *
 * @author ycj
 */
@Mapper
public interface TicketMapper {

    /**
     * 新增
     *
     * @param ticket
     * @return
     */
    int add(Ticket ticket);

    /**
     * 校验名称
     *
     * @param ticketName
     * @return
     */
    int validByName(@Param("ticketName") String ticketName);

    /**
     * 校验名称
     *
     * @param id
     * @param ticketName
     * @return
     */
    int validByNameAndId(@Param("id") Long id, @Param("ticketName") String ticketName);

    /**
     * 查询列表
     *
     * @param searchKey
     * @return
     */
    Page<Ticket> listPage(@Param("searchKey") String searchKey);

    /**
     * 修改优惠券
     *
     * @param ticket
     * @return
     */
    int modify(Ticket ticket);

    /**
     * 根据主键删除记录（修改状态）
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询优惠券
     *
     * @param id
     * @return
     */
    Ticket findById(@Param("id") Long id);

    /**
     * 根据主键批量删除优惠券
     *
     * @param idList
     * @return
     */
    Integer batchDelete(@Param("idList") List<Long> idList);
}
