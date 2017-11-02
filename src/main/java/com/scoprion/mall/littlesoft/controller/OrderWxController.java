package com.scoprion.mall.littlesoft.controller;
import com.scoprion.mall.littlesoft.service.order.OrderWxService;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@RestController
@RequestMapping("wx/order")
public class OrderWxController {
    @Autowired
    private  OrderWxService orderWxService;

    /**
     * 分页获取我的订单列表
     *
     * @param userId
     * @param status
     * 0 全部
     * 1 待付款
     * 2 待发货
     * 3 待收货
     * 4 已完成
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/my-order",method = RequestMethod.GET)
    public PageResult getMyOrderList(Long userId, String status, Integer pageNo, Integer pageSize){
        return orderWxService.getMyOrder(userId,status,pageNo,pageSize);
    }
}
