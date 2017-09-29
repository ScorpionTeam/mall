package com.scoprion.mall.order.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.order.mapper.OrderMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/9/29.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @param status   订单状态
     * @param userId   用户id
     * @return
     */
    @Override
    public PageResult findByPage(int pageNo, int pageSize, String status, Long userId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Order> result = orderMapper.findByPage(status, userId);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }


}
