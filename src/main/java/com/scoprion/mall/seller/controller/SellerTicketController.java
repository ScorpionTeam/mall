package com.scoprion.mall.seller.controller;


import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.domain.TicketExt;
import com.scoprion.mall.seller.service.ticket.SellerTicketService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by fk
 * @created on 2017/12/13.
 */
@RestController
@RequestMapping("/seller/ticket")
public class SellerTicketController {

    @Autowired
    private SellerTicketService sellerTicketService;

    /**
     * 优惠卷列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerId
     * @param searchKey
     * @return
     */
    @GetMapping("/findAll")
    public PageResult findAll( int pageNo, int pageSize, Long sellerId,String searchKey) {
        return sellerTicketService.findAll(pageNo, pageSize, sellerId,searchKey);
    }


    /**
     * 商家创建优惠券
     *
     * @param ticket
     * @return
     * @throws Exception
     */
    @Access
    @ApiOperation(value = "创建优惠券")
    @PostMapping("/add")
    public BaseResult add(@RequestBody TicketExt ticket) throws Exception{
        return sellerTicketService.add(ticket);
    }

    /**
     * 修改优惠券
     *
     * @param ticket
     * @return
     */
    @PostMapping("/modify")
    public BaseResult modify(@RequestBody Ticket ticket){
        return sellerTicketService.modify(ticket);
    }

    /**
     * 根据主键删除优惠券
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteById/{id}")
    public BaseResult deleteById(@PathVariable("id") Long id){
        return sellerTicketService.deleteById(id);
    }

    /**
     * 根据主键查询详情
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public BaseResult findById(@PathVariable("id") Long id){
        return sellerTicketService.findById(id);
    }


    /**
     * 批量删除
     *
     * @param object
     * @return
     */
    @PostMapping("/delete")
    public BaseResult delete(@RequestBody JSONObject object){
        List<Long>idList=object.getJSONArray("idList").toJavaList(Long.class);
        return sellerTicketService.delete(idList);
    }
}
