package com.scoprion.mall.wx.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Estimate;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
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
     * @param wxCode      微信code
     * @param orderStatus 订单状态
     * @return
     */
    @Override
    public PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus) {

        //暂时使用直接传userId的方式 查询订单列表
        String userId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        if ("0".equals(orderStatus)) {
            orderStatus = null;
        }
        Page<Order> page = wxOrderMapper.findByUserId(userId, orderStatus);
        return new PageResult(page);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResult findByOrderId(Long orderId) {
        Order order = wxOrderMapper.findByOrderId(orderId);
        if (null == order) {
            return BaseResult.notFound();
        }
        return BaseResult.success(order);
    }

    /**
     * 退款
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResult refund(Long orderId) {
        wxOrderMapper.updateByOrderID(orderId, "5");
        return BaseResult.success("申请成功");
    }


    /**
     * 签收后评价
     * @param estimate
     * @return
     */
    @Override
    public BaseResult estimate(Estimate estimate) {
        if(estimate.getId() == null) {
            return BaseResult.notFound();
        }
        int result = wxOrderMapper.estimate(estimate);
        if(result > 0) {
            return BaseResult.success("评价成功");
        }
        return BaseResult.error("estimate_fail","评价失败");
    }

    /**
     * 投诉
     * @param id
     * @param complain
     * @return
     */
    @Override
    public BaseResult complain(Long id, String complain) {
        int result = wxOrderMapper.complain(id, complain);
        if(result > 0) {
            return BaseResult.success("投诉成功");
        }
        return BaseResult.error("complain_fail", "投诉失败");
    }


}
