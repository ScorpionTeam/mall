package com.scoprion.mall.seller.controller;

import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.request.OrderRequestParams;
import com.scoprion.mall.seller.service.SellerOrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/findByCondition")
    PageResult findByCondition(OrderRequestParams requestParams) {
        return sellerOrderService.findByCondition(requestParams);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    BaseResult findById(@PathParam("id") Long id) {
        return sellerOrderService.findById(id);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @PostMapping("/modify")
    BaseResult modify(Order order) {
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
    @PostMapping("/refund/{orderId}/{flag}/{remark}/{refundFee}")
    BaseResult refund(@PathParam("orderId") Long orderId,
                      @PathParam("flag") String flag,
                      @PathParam("remark") String remark,
                      @PathParam("refundFee") Integer refundFee) {
        return sellerOrderService.refund(orderId, flag, remark, refundFee);
    }


    /**
     * 退货
     *
     * @param orderId 订单id
     * @param count   退货数量
     * @return
     */
    @PostMapping("/refund/{orderId}/{count}")
    BaseResult goodReject(@PathParam("orderId") Long orderId,
                          @PathParam("count") Integer count) {
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
    @PostMapping("/sendGood/{orderId}/{deliveryNo}/{expressName}/{expressNo}/{senderId}")
    BaseResult sendGood(@PathParam("orderId") Long orderId,
                        @PathParam("deliveryNo") String deliveryNo,
                        @PathParam("expressName") String expressName,
                        @PathParam("expressNo") String expressNo,
                        @PathParam("senderId") Long senderId) {
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
    @GetMapping("/sendGood/{pageNo}/{pageSize}/{orderId}")
    PageResult findOrderLogByOrderId(@PathParam("pageNo") Integer pageNo,
                                     @PathParam("pageSize") Integer pageSize,
                                     @PathParam("orderId") Long orderId) {
        return sellerOrderService.findOrderLogByOrderId(pageNo, pageSize, orderId);
    }
}
