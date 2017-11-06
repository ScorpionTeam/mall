package com.scoprion.mall.littlesoft.service.pay;

import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

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
     * @param good
     * @return
     */
    private Order constructOrder(Good good) {
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);

        return order;
    }

}
