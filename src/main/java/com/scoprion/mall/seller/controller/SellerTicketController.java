package com.scoprion.mall.seller.controller;

import com.scoprion.mall.seller.service.ticket.SellerTicketService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @GetMapping("/findAll/{pageNo}/{pageSize}/{sellerId}")
    public PageResult findAll(@PathVariable("pageNo") int pageNo,
                              @PathVariable("pageSize") int pageSize,
                              @PathVariable("sellerId") Long sellerId) {
        return sellerTicketService.findAll(pageNo, pageSize, sellerId);
    }
}
