package com.scoprion.mall.littlesoft.service.pay;

import com.alibaba.fastjson.JSON;
import com.scoprion.mall.backstage.mapper.DeliveryMapper;
import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.OrderNoUtil;
import com.scoprion.wxpay.AuthorizationCode;
import com.scoprion.wxpay.WxPayConfig;
import com.scoprion.wxpay.WxPayUtil;
import com.scoprion.wxpay.WxUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 微信预下单
     *
     * @param wxOrderRequestData
     * @param wxCode
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult preOrder(WxOrderRequestData wxOrderRequestData, String wxCode, String ipAddress) {

        //查询商品库存
        Good good = goodMapper.findById(wxOrderRequestData.getGoodId());
        if (null == good || good.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        GoodSnapshot goodSnapshot = constructSnapshot(good);
        String wxOrderNo = "";
        //查询收货地址
        Delivery delivery = deliveryMapper.findById(wxOrderRequestData.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("not_found_address", "收货地址出错");
        }
        Order order = constructOrder(good, goodSnapshot.getId(), delivery, wxOrderNo);
        int orderResult = orderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("pre_order_error", "下单出错");
        }
        //查询用户openid
        String openid = findOpenID(wxCode);
        String xmlString = preOrderSend(good.getGoodName(), good.getDescription(), "妆口袋", openid, order.getOrderNo(),
                ipAddress);
        //生成预付款订单
        String wxOrderResponse = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL,"GET",xmlString);

        return null;
    }

    /**
     * 构造订单
     *
     * @param good           商品
     * @param goodSnapShotId 快照id
     * @param delivery       配送地址
     * @param wxOrderNo      微信订单号
     * @return
     */
    private Order constructOrder(Good good, Long goodSnapShotId, Delivery delivery, String wxOrderNo) {
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setGoodSnapShotId(goodSnapShotId);
        order.setPayType("");
        order.setOrderType("2");
        order.setOrderStatus("1");
        order.setDeliveryId(delivery.getId());
        order.setWxOrderNo(wxOrderNo);
        BeanUtils.copyProperties(delivery, order);
        return order;
    }

    /**
     * 构造商品快照
     *
     * @param good
     * @return
     */
    private GoodSnapshot constructSnapshot(Good good) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(good, goodSnapshot);
        return goodSnapshot;
    }

    /**
     * 预付款订单签名
     *
     * @param body       商品描述
     * @param detail     商品详情
     * @param attach
     * @param openid     用户openid
     * @param outTradeNo 商户订单号
     * @param ipAddress  ip地址
     * @return
     */
    private String preOrderSend(String body, String detail, String attach, String openid, String outTradeNo, String ipAddress) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APPID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("device_info", "10000");
        map.put("nonce_str", WxUtil.createRandom(false, 10));
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("spbill_create_ip", ipAddress);
        map.put("notify_url", WxPayConfig.NOTIFY_URL);
        map.put("trade_type", "JSAPI");
        String signTemp = WxPayUtil.sort(map);
        String sign = WxUtil.MD5(signTemp).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.MapConvertToXML(map);
    }

    /**
     * 查询openid
     *
     * @param wxCode
     * @return
     */
    private String findOpenID(String wxCode) {

        String apiUrl = WxPayConfig.OPEN_ID_URL
                + "appid=" + WxPayConfig.APPID
                + "&secret=" + WxPayConfig.APPSECRET
                + "&js_code=" + wxCode
                + "&grant_type=authorization_code";
        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        AuthorizationCode authorizationCode = JSON.parseObject(response, AuthorizationCode.class);
        return authorizationCode.getOpenid();
    }

}
