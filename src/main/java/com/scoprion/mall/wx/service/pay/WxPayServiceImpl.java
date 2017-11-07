package com.scoprion.mall.wx.service.pay;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.OrderLog;
import com.scoprion.mall.domain.WxOrderRequestData;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.GoodSnapShotWxMapper;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.mall.wx.mapper.WxOrderLogMapper;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.pay.domain.AuthorizationCode;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.OrderNoUtil;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private WxGoodMapper wxGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    @Autowired
    private GoodSnapShotWxMapper goodSnapShotWxMapper;

    /**
     * 微信预下单
     *
     * @param wxOrderRequestData
     * @param wxCode
     * @param ipAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult preOrder(WxOrderRequestData wxOrderRequestData, String wxCode, String ipAddress) {

        //查询商品库存
        Good good = wxGoodMapper.findById(wxOrderRequestData.getGoodId());
        if (null == good || good.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        GoodSnapshot goodSnapshot = constructSnapshot(good);
        goodSnapShotWxMapper.add(goodSnapshot);
        //查询收货地址
        Delivery delivery = wxDeliveryMapper.findById(wxOrderRequestData.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("not_found_address", "收货地址出错");
        }
        //查询用户openid
        String openid = findOpenID(wxCode);
        Order order = constructOrder(good, goodSnapshot.getId(), delivery, wxOrderRequestData, openid);
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("pre_order_error", "下单出错");
        }
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成预付款订单", ipAddress);
        wxOrderLogMapper.add(orderLog);
        String xmlString = preOrderSend(good.getGoodName(), good.getDescription(), "妆口袋", openid, order.getOrderNo(),
                ipAddress, wxOrderRequestData.getTotalFee().intValue());
        //生成预付款订单
        String wxOrderResponse = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "GET", xmlString);
        System.out.println(wxOrderResponse);
        //将xml返回信息转换为bean
        UnifiedOrderResponseData unifiedOrderResponseData = WxPayUtil.castXMLStringToUnifiedOrderResponseData(
                wxOrderResponse);
        //修改订单预付款订单号
        wxOrderMapper.updateOrderForWxOrderNo(order.getId(), unifiedOrderResponseData.getPrepay_id());
        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;
        //随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);
        String paySign = paySign(timeStamp, nonceStr, unifiedOrderResponseData.getPrepay_id());
        unifiedOrderResponseData.setPaySign(paySign);
        unifiedOrderResponseData.setNonce_str(nonceStr);
        unifiedOrderResponseData.setTimeStamp(String.valueOf(timeStamp));
        return BaseResult.success(unifiedOrderResponseData);
    }

    /**
     * 去支付
     *
     * @param wxCode
     * @param orderId
     * @return
     */
    @Override
    public BaseResult pay(String wxCode, Long orderId) {
        String openid = findOpenID(wxCode);
        //根据openid查询用户订单信息
        String prepayId = wxOrderMapper.findPrepayIdByOpenid(openid, orderId);
        if (StringUtils.isEmpty(prepayId)) {
            return BaseResult.error("query_error", "查询订单出错");
        }
        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;
        //随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);
        String paySign = paySign(timeStamp, nonceStr, prepayId);
        Map<String, String> resultMap = new HashMap<>(16);
        resultMap.put("appId", WxPayConfig.APP_ID);
        resultMap.put("timeStamp", timeStamp.toString());
        resultMap.put("nonceStr", nonceStr);
        resultMap.put("package", "prepay_id=" + prepayId);
        resultMap.put("signType", "MD5");
        resultMap.put("paySign", paySign);
        return BaseResult.success(JSON.toJSON(resultMap));
    }

    /**
     * 支付成功回调
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    @Override
    public BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData) {
        //修改订单状态
        wxOrderMapper.updateOrderStatusAndPayStatus(unifiedOrderNotifyRequestData.getTime_end(),
                unifiedOrderNotifyRequestData.getOut_trade_no());
        //记录订单日志
        OrderLog orderLog = constructOrderLog(unifiedOrderNotifyRequestData.getOut_trade_no(), "付款", null);
        wxOrderLogMapper.add(orderLog);
        //库存扣减
        Order order = wxOrderMapper.findByWxOrderNo(unifiedOrderNotifyRequestData.getOut_trade_no());
        wxGoodMapper.updateGoodStockById(order.getGoodId(), order.getCount());
        //积分 扣减 新增

        //优惠券扣减
        //

        //
        return BaseResult.success("支付回调成功");
    }

    /**
     * 构造订单
     *
     * @param good               商品
     * @param goodSnapShotId     快照id
     * @param delivery           配送地址
     * @param wxOrderRequestData 下单参数
     * @param userId
     * @return
     */
    private Order constructOrder(Good good, Long goodSnapShotId, Delivery delivery, WxOrderRequestData wxOrderRequestData, String userId) {
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setUserId(userId);
        order.setOrderNo(orderNo);
        order.setGoodSnapShotId(goodSnapShotId);
        order.setPayType("");
        order.setOrderType("2");
        order.setOrderStatus("1");
        order.setGoodName(good.getGoodName());
        order.setDeliveryId(delivery.getId());
        order.setTotalFee(wxOrderRequestData.getTotalFee());
        order.setGoodFee(wxOrderRequestData.getGoodPrice());
        order.setCount(wxOrderRequestData.getCount());
        order.setMessage(wxOrderRequestData.getMessage());
        order.setGoodId(good.getId());
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
    private String preOrderSend(String body, String detail, String attach, String openid, String outTradeNo, String ipAddress, int totalFee) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("device_info", "10000");
        map.put("nonce_str", WxUtil.createRandom(false, 10));
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", totalFee);
        map.put("spbill_create_ip", ipAddress);
        map.put("notify_url", WxPayConfig.NOTIFY_URL);
        map.put("trade_type", "JSAPI");
        String signTemp = WxPayUtil.sort(map);
        String sign = WxUtil.MD5(signTemp).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.MapConvertToXML(map);
    }

    /**
     * 调起支付  签名
     *
     * @param timeStamp
     * @param nonceStr
     * @param prepayId
     * @return
     */
    private String paySign(Long timeStamp, String nonceStr, String prepayId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appId", WxPayConfig.APP_ID);
        map.put("nonceStr", nonceStr);
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", "MD5");
        map.put("timeStamp", timeStamp);
        return WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
    }

    /**
     * 查询openid
     *
     * @param wxCode
     * @return
     */
    private String findOpenID(String wxCode) {

        String apiUrl = WxPayConfig.OPEN_ID_URL
                + "appid=" + WxPayConfig.APP_ID
                + "&secret=" + WxPayConfig.APP_SECRET
                + "&js_code=" + wxCode
                + "&grant_type=authorization_code";
        String response = WxUtil.httpsRequest(apiUrl, "GET", null);
        AuthorizationCode authorizationCode = JSON.parseObject(response, AuthorizationCode.class);
        return authorizationCode.getOpenid();
    }

    /**
     * 订单日志构造
     *
     * @param orderNo   订单no
     * @param action    动作
     * @param ipAddress IP地址
     * @return
     */
    private OrderLog constructOrderLog(String orderNo, String action, String ipAddress) {
        OrderLog orderLog = new OrderLog();
        orderLog.setAction(action);
        orderLog.setOrderNo(orderNo);
        orderLog.setIpAddress(ipAddress);
        return orderLog;
    }

}
