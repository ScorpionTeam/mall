package com.scoprion.mall.backstage.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.order.OrderService;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.request.OrderRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ycj
 * @version V1.0 <订单控制器>
 * @date 2017-11-07 10:21
 */
@RestController
@RequestMapping("/backstage/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


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
    @RequestMapping(value = "/sendGood", method = RequestMethod.POST)
    public BaseResult sendGood(Long orderId, String deliveryNo, String expressName, String expressNo, Long senderId) {
        return orderService.sendGood(orderId, deliveryNo, expressName, expressNo, senderId);
    }


    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @Access
    @ApiOperation(value = "修改订单")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody Order order) {
        return orderService.modify(order);
    }

    @Access
    @ApiOperation(value = "订单列表")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    public PageResult findByCondition(@RequestBody OrderRequestParams requestParams) {
        return orderService.findByCondition(requestParams);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation(value = "根据id查询详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return orderService.findById(id);
    }

    /**
     * 根据订单id查订单日志
     *
     * @param orderId
     * @return
     */
    @Access
    @ApiOperation(value = "根据订单id查订单日志")
    @RequestMapping(value = "/findOrderLogByOrderId", method = RequestMethod.GET)
    public PageResult findOrderLogByOrderId(Integer pageNo, Integer pageSize, Long orderId) {
        return orderService.findOrderLogByOrderId(pageNo, pageSize, orderId);
    }


    /**
     * @param orderId   订单id
     * @param flag      AGREE 同意    REFUSE 拒绝
     * @param remark    备注
     * @param refundFee 退款金额
     * @return
     * @throws Exception
     */
    @Access
    @RequestMapping(value = "/audit/refund", method = RequestMethod.POST)
    public BaseResult refund(Long orderId, String flag, String remark, int refundFee) throws Exception {
        return orderService.refund(orderId, flag, remark, refundFee);
    }


    /**
     * 退货
     *
     * @param orderId 订单id
     * @param count   退货数量
     * @return
     */
    @Access
    @RequestMapping(value = "/goodReject", method = RequestMethod.GET)
    public BaseResult goodReject(Long orderId, Integer count) {
        return orderService.goodReject(orderId, count);
    }
}
