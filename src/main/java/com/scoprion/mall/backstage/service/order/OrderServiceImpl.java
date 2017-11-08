package com.scoprion.mall.backstage.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created on 2017/9/29.
 *
 * @author ycj
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

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
    @Override
    public PageResult listPage(Integer pageNo, Integer pageSize, String payType, String orderType,
                               String orderStatus, String searchKey, String startDate, String endDate, String phone) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(startDate)) {
            startDate = null;
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = null;
        }
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Order> orderPage = orderMapper.listPage(payType, orderType, orderStatus, searchKey, startDate, endDate, phone);
        if (orderPage == null) {
            return new PageResult(new ArrayList());
        }
        return new PageResult(orderPage);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Order order = orderMapper.findById(id);
        if (order == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(order);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @Override
    public BaseResult modify(Order order) {
        if (order.getId() == null) {
            return BaseResult.parameterError();
        }
        orderMapper.modify(order);
        return BaseResult.success("修改成功");
    }
}
