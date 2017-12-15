package com.scoprion.mall.seller.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.request.OrderRequestParams;
import com.scoprion.mall.seller.service.order.SellerOrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:26
 */
@RestController
@RequestMapping("seller/order")
public class SellerOrderController {

    @Autowired
    SellerOrderService sellerOrderService;

    /**
     * 订单列表
     *
     * @param requestParams
     * @return
     */
    @Access
    @ApiOperation(value = "订单列表")
    @PostMapping("/findByCondition")
    public PageResult findByCondition(@RequestBody OrderRequestParams requestParams) {
        return sellerOrderService.findByCondition(requestParams);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation(value = "根据id查询详情")
    @GetMapping("/findById/{id}")
    public BaseResult findById(@PathVariable("id") Long id) {
        return sellerOrderService.findById(id);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @Access
    @ApiOperation(value = "修改订单")
    @PostMapping("/modify")
    public BaseResult modify(@RequestBody Order order) {
        return sellerOrderService.modify(order);
    }

    /**
     * 退款
     *
     * @param orderId
     * @param flag
     * @param remark
     * @param refundFee
     * @return
     */
    @Access
    @ApiOperation(value = "退款")
    @PostMapping("/refund")
    public BaseResult refund(Long orderId, String flag, String remark, Integer refundFee) {
        return sellerOrderService.refund(orderId, flag, remark, refundFee);
    }


    /**
     * 退货
     *
     * @param orderId 订单id
     * @param count   退货数量
     * @return
     */
    @Access
    @ApiOperation(value = "退货")
    @PostMapping("/refund/{orderId}/{count}")
    public BaseResult goodReject(@PathVariable("orderId") Long orderId,
                                 @PathVariable("count") Integer count) {
        return sellerOrderService.goodReject(orderId, count);
    }

    /**
     * 商品发货
     *
     * @param orderId     订单号
     * @param deliveryNo  运单号
     * @param expressName 快递公司
     * @param expressNo   快递公司编号
     * @param senderId    寄件人Id
     * @return
     */
    @Access
    @ApiOperation(value = "商品发货")
    @PostMapping("/sendGood")
    public BaseResult sendGood( Long orderId,
                                String deliveryNo,
                                String expressName,
                                String expressNo,
                                Long senderId) {
        return sellerOrderService.sendGood(orderId, deliveryNo, expressName, expressNo, senderId);
    }

    /**
     * 根据订单id查订单日志
     *
     * @param pageNo
     * @param pageSize
     * @param orderId
     * @return
     */
    @Access
    @ApiOperation(value = "根据订单id查订单日志")
    @GetMapping("/sendGood/{pageNo}/{pageSize}/{orderId}")
    public PageResult findOrderLogByOrderId(@PathVariable("pageNo") Integer pageNo,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @PathVariable("orderId") Long orderId) {
        return sellerOrderService.findOrderLogByOrderId(pageNo, pageSize, orderId);
    }
}
