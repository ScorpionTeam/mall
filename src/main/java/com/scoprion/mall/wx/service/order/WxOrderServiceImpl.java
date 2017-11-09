package com.scoprion.mall.wx.service.order;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.AuthorizationCode;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
        String userId = openid(wxCode);
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
        return BaseResult.success("退款已提交申请");
    }

    /**
     * 获取openid
     *
     * @param wxCode
     * @return
     */
    private String openid(String wxCode) {
        String apiUrl = WxPayConfig.OPEN_ID_URL
                + "appid=" + WxPayConfig.APP_ID
                + "&secret=" + WxPayConfig.APP_SECRET
                + "&js_code=" + wxCode
                + "&grant_type=authorization_code";
        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        AuthorizationCode authorizationCode = JSON.parseObject(response, AuthorizationCode.class);
        return authorizationCode.getOpenid();
    }


}
