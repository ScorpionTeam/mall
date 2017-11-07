package com.scoprion.mall.wx.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
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

    /**
     * 我的订单
     *
     * @param pageNo      当前页
     * @param pageSize    每页条数
     * @param userId      用户id
     * @param orderStatus 订单状态
     * @return
     */
    @Override
    public PageResult findByUserId(int pageNo, int pageSize, String userId, String orderStatus) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Order> page = wxOrderMapper.findByUserId(userId, orderStatus);
        return new PageResult(page);
    }


}
