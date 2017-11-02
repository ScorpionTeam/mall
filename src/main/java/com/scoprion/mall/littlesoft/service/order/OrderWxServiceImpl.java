package com.scoprion.mall.littlesoft.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.littlesoft.mapper.OrderWxMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Service
public class OrderWxServiceImpl implements  OrderWxService {
    @Autowired
    private OrderWxMapper orderWxMapper;

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
    @Override
    public PageResult getMyOrder(Long userId, String status, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        //若userId,status传空或没传,直接返回
        if(StringUtils.isEmpty(userId.toString())||StringUtils.isEmpty(status)){
            return  new PageResult();
        }
        Page<Order> page = orderWxMapper.getOrderList(userId,status);
        return new PageResult(page);
    }
}
