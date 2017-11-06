package com.scoprion.mall.littlesoft.service.orders;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.OrderLog;
import com.scoprion.mall.littlesoft.mapper.WxOrderMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    private WxOrderMapper wxOrderMapper;
    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize,Long userId,String orderStatus) {
        PageHelper.startPage(pageNo,pageSize);
        //若userId,status传空或没传,直接返回
        if (StringUtils.isEmpty(userId.toString()) || StringUtils.isEmpty(orderStatus)) {
            return new PageResult();
        }
        Page<Order> page=wxOrderMapper.findByCondition (userId,orderStatus);
        return new PageResult(page);
    }

    @Override
    public BaseResult findByCondition(Long orderId) {
        if(StringUtils.isEmpty(orderId.toString())){
            return BaseResult.error("orderLog_fail","无订单日志");
        }
        OrderLog result=wxOrderMapper.findByCondition(orderId);
        return BaseResult.success(result);
    }
}
