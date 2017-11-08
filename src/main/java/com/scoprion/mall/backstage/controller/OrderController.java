package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.order.OrderService;
import com.scoprion.mall.domain.Order;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 修改订单
     *
     * @param order
     * @return
     */
    @ApiOperation(value = "修改订单")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(Order order) {
        return orderService.modify(order);
    }


    /**
     * 订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param payType     支付类型0 支付宝1 微信2 信用卡3 储蓄卡
     * @param orderType   订单类型 1pc订单  2手机订单
     * @param orderStatus 状态  0 全部  1 待付款   2 待发货  3 待收货 4 已完成
     * @param searchKey   模糊查询信息
     * @param startDate   开始时间
     * @param endDate     结束时间
     * @param phone       收件人手机号
     * @return
     */
    @ApiOperation(value = "订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResult list(Integer pageNo, Integer pageSize, String payType, String orderType,
                           String orderStatus, String searchKey, String startDate, String endDate, String phone) {
        return orderService.listPage(pageNo, pageSize, payType, orderType, orderStatus, searchKey, startDate, endDate,
                phone);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return orderService.findById(id);
    }

    /**
     * 退款处理
     *
     * @param orderId   订单id
     * @param flag      0 拒绝  1 通过
     * @param remark    退款备注
     * @param refundFee 退款金额
     * @return
     */
    @RequestMapping(value = "/audit/refund", method = RequestMethod.POST)
    public BaseResult refund(Long orderId, String flag, String remark, int refundFee) {

        return null;
    }
}
