package com.scoprion.mall.littlesoft.service.pay;

import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private GoodMapper goodMapper;

    /**
     * 微信预下单
     *
     * @param wxOrderRequestData
     * @return
     */
    @Override
    public BaseResult preOrder(WxOrderRequestData wxOrderRequestData) {

        //查询商品库存
        Good good = goodMapper.findById(wxOrderRequestData.getGoodId());
        if (null == good || good.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }

        return null;
    }

    /**
     * 构造订单
     *
     * @param good           商品
     * @param goodSnapShotId 快照id
     * @param delivery       配送地址
     * @return
     */
    private Order constructOrder(Good good, Long goodSnapShotId, Delivery delivery) {
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setGoodSnapShotId(goodSnapShotId);
        order.setPayType("");
        order.setOrderType("2");
        order.setOrderStatus("1");
        order.setDeliveryId(delivery.getId());
        BeanUtils.copyProperties(delivery, order);
        return order;
    }

}
