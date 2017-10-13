package com.scoprion.mall.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.mapper.GoodMapper;
import com.scoprion.mall.mapper.OrderMapper;
import com.scoprion.result.BaseResult;
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

    @Autowired
    private GoodMapper goodMapper;

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

    /**
     * 下单
     *
     * @param orderConfirm
     * @return
     */
    @Override
    public synchronized BaseResult orderConfirm(OrderConfirm orderConfirm) {
        //不管付款成功与否  都创建收货人信息

        Good good = goodMapper.findById(orderConfirm.getGoodId());
        if (null == good) {
            return BaseResult.notFound();
        }
        if (good.getStock() <= 0) {
            return BaseResult.error("configm_fail", "库存不足.");
        }
        return null;
    }


}
