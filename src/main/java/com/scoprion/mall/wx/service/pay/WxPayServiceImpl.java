package com.scoprion.mall.wx.service.pay;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.wx.mapper.*;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.OrderNoUtil;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
@Service
public class WxPayServiceImpl implements WxPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    private WxGoodMapper wxGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    @Autowired
    private WxGoodSnapShotMapper wxGoodSnapShotMapper;

    @Autowired
    private WxPointMapper wxPointMapper;

    @Autowired
    private WxPointLogMapper wxPointLogMapper;

    @Autowired
    private WxTicketSnapshotMapper wxTicketSnapshotMapper;

    @Autowired
    private WxTicketMapper wxTicketMapper;

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
        //查询用户openid
        String openid = WxUtil.getOpenId(wxCode);
        //积分判断
        BaseResult x = checkPoint(wxOrderRequestData, openid);
        if (x != null) {
            return x;
        }
        //使用优惠券
        if (Constant.STATUS_ONE.equals(wxOrderRequestData.getUseTicket())) {
            TicketSnapshot ticketSnapshot = wxTicketSnapshotMapper.findByUserIdAndTicketId(
                    wxOrderRequestData.getTicket());
            if (ticketSnapshot == null) {
                return BaseResult.error("error", "请先领取优惠券");
            }
            if (Constant.STATUS_ONE.equals(ticketSnapshot.getStatus())) {
                return BaseResult.error("error", "优惠券已经使用过了");
            }
            if (ticketSnapshot.getStartDate().after(new Date())) {
                return BaseResult.error("error", "优惠券未到使用日期");
            }
            if (ticketSnapshot.getEndDate().before(new Date())) {
                return BaseResult.error("error", "优惠券已过期");
            }
            //优惠券状态改为已使用
            wxTicketSnapshotMapper.modifyStatus(Constant.STATUS_ONE, ticketSnapshot.getId());
        }
        //查询商品库存
        Goods goods = wxGoodMapper.findById(wxOrderRequestData.getGoodId());
        if (null == goods || goods.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            //商品处于下架状态，不能下单
            return BaseResult.error("can_not_order", "商品已下架");
        }
        //查询收货地址
        Delivery delivery = wxDeliveryMapper.findById(wxOrderRequestData.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("not_found_address", "收货地址有误");
        }
        //价格判断
        int unitPrice = wxOrderRequestData.getOrderFee() / wxOrderRequestData.getCount();
        if (goods.getPrice() != unitPrice) {
            return BaseResult.error("not_found_address", "商品信息已过期，请重新下单");
        }
        //商品快照
        GoodSnapshot goodSnapshot = constructSnapshot(goods);
        wxGoodSnapShotMapper.add(goodSnapshot);

        Order order = constructOrder(goods, goodSnapshot.getId(), delivery, wxOrderRequestData, openid);
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("pre_order_error", "下单出错");
        }

        //系统内部生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成预付款订单", ipAddress, order.getId());
        wxOrderLogMapper.add(orderLog);
        String nonce_str = WxUtil.createRandom(false, 10);
        String xmlString = preOrderSend(goods.getGoodName(),
                "妆口袋",
                openid,
                order.getOrderNo(),
                wxOrderRequestData.getPaymentFee(),
                nonce_str);
        //生成预付款订单
        String wxOrderResponse = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", xmlString);
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
     * 校验优惠券
     *
     * @param wxOrderRequestData
     * @return
     */
    private BaseResult checkTicket(WxOrderRequestData wxOrderRequestData) {
        if (Constant.STATUS_ONE.equals(wxOrderRequestData.getUseTicket())) {
            TicketSnapshot ticketSnapshot = wxTicketSnapshotMapper.findByUserIdAndTicketId(
                    wxOrderRequestData.getTicket());
            if (ticketSnapshot == null) {
                return BaseResult.error("error", "请先领取优惠券");
            }
            if (Constant.STATUS_ONE.equals(ticketSnapshot.getStatus())) {
                return BaseResult.error("error", "优惠券已经使用过了");
            }
            if (ticketSnapshot.getStartDate().after(new Date())) {
                return BaseResult.error("error", "优惠券未到使用日期");
            }
            if (ticketSnapshot.getEndDate().before(new Date())) {
                return BaseResult.error("error", "优惠券已过期");
            }
            //优惠券状态改为已使用
            wxTicketSnapshotMapper.modifyStatus(Constant.STATUS_ONE, ticketSnapshot.getId());
        }
        return null;
    }

    /**
     * 校验积分
     *
     * @param wxOrderRequestData
     * @param openid
     * @return
     */
    private BaseResult checkPoint(WxOrderRequestData wxOrderRequestData, String openid) {
        Point point = wxPointMapper.findByUserId(openid);
        if (point == null) {
            //没有积分
            if (wxOrderRequestData.getPoint() > 0) {
                return BaseResult.error("pay_error", "下单失败，没有可使用的积分");
            }
        } else if (wxOrderRequestData.getPoint() > point.getPoint()) {
            //有积分，使用量超过已有积分
            return BaseResult.error("pay_error", "下单失败,积分不足");
        }
        return null;
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
        String openid = WxUtil.getOpenId(wxCode);
        Order order = wxOrderMapper.findByOrderId(orderId);
        if (order == null) {
            return BaseResult.error("can_not_order", "找不到订单");
        }
        if (!Constant.STATUS_ONE.equals(order.getOrderStatus())) {
            return BaseResult.error("order_status_error", "订单状态异常");
        }
        long createTime = order.getCreateDate().getTime();
        long result = System.currentTimeMillis() - createTime;
        if (result > Constant.TIME_TWO_HOUR) {
            //关闭订单
            wxOrderMapper.updateByOrderID(orderId, "6");
            return BaseResult.error("order_timeout", "订单已超时，请重新下单");
        }
        //查询商品库存
        Goods goods = wxGoodMapper.findById(order.getGoodId());
        if (null == goods || goods.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            //商品处于下架状态，不能下单
            return BaseResult.error("can_not_order", "商品已下架");
        }

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
        Order order = wxOrderMapper.findByWxOrderNo(unifiedOrderNotifyRequestData.getOut_trade_no());
        LOGGER.info("微信支付回调----callback");
        if (order == null) {
            LOGGER.info("订单为空，查询不到订单信息");
        }
        //判断是否成功接收回调
        if (order != null && null == order.getPayDate()) {
            //修改订单状态 以及微信订单号
            wxOrderMapper.updateOrderStatusAndPayStatus(unifiedOrderNotifyRequestData.getTime_end(),
                    unifiedOrderNotifyRequestData.getOut_trade_no(),
                    unifiedOrderNotifyRequestData.getTransaction_id());
            //记录订单日志
            OrderLog orderLog = constructOrderLog(unifiedOrderNotifyRequestData.getOut_trade_no(), "付款",
                    null, order.getId());
            wxOrderLogMapper.add(orderLog);
            //库存扣减
            wxGoodMapper.updateGoodStockById(order.getGoodId(), order.getCount());
            //积分扣减、增加
            BaseResult operateResult = operatePoint(order);
            if (operateResult != null) {
                return operateResult;
            }
            //销量
            wxGoodMapper.updateSaleVolume(order.getCount(), order.getGoodId());
        }
        return BaseResult.success("支付回调成功");
    }

    /**
     * @param goodId     商品id
     * @param deliveryId 收件人id
     * @param buyNum     购买数量
     * @param message    买家留言
     * @param orderType  订单类型
     * @param useTicket  是否使用优惠券
     * @param paymentFee 实付金额
     * @param orderFee   订单金额
     * @param reduceFee  优惠金额
     * @param freightFee 运费
     * @param goodFee    商品金额
     * @return
     */
    @Override
    public BaseResult pressureTest(Long goodId, Long deliveryId, int buyNum, String message, String orderType, String useTicket, int paymentFee, int orderFee, int reduceFee, int freightFee, int goodFee) {
        Goods goods = wxGoodMapper.findById(goodId);
        if (null == goods || goods.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            //商品处于下架状态，不能下单
            return BaseResult.error("can_not_order", "商品已下架");
        }
        //查询收货地址
        Delivery delivery = wxDeliveryMapper.findById(deliveryId);
        if (null == delivery) {
            return BaseResult.error("not_found_address", "收货地址出错");
        }
        //价格判断
        int unitPrice = orderFee / buyNum;
        if (goods.getPrice() != unitPrice) {
            return BaseResult.error("not_found_address", "商品信息已过期，请重新下单");
        }
        //商品快照
        GoodSnapshot goodSnapshot = constructSnapshot(goods);
        wxGoodSnapShotMapper.add(goodSnapshot);
        String orderNo = OrderNoUtil.getOrderNo();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUseTicket("0");
        order.setPaymentFee(paymentFee);
        order.setFreightFee(freightFee);
        order.setReduceFee(reduceFee);
        order.setGoodFee(goodFee);
        order.setGoodId(goodId);
        order.setUserId("test");
        order.setGoodName(goods.getGoodName());
        order.setMessage(message);
        order.setCount(buyNum);
        order.setWxOrderNo("0000000");
        order.setPhone(delivery.getPhone());
        order.setAddress(delivery.getAddress());
        order.setRecipients(delivery.getRecipients());
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("pre_order_error", "下单出错");
        }

        //系统内部生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成预付款订单", null, order.getId());
        wxOrderLogMapper.add(orderLog);
        LOGGER.info("压力测试订单@***************订单号-", orderNo);
        return BaseResult.success("order_confirm");
    }

    private BaseResult operatePoint(Order order) {
        //积分 扣减
        Point point = wxPointMapper.findByUserId(order.getUserId());
        if (point == null) {
            //第一次购买，
            point = new Point();
        }
        if (order.getOperatePoint() > point.getPoint()) {
            return BaseResult.error("pay_error", "支付失败积分不足");
        }
        //积分扣减
        subtractPointLog(order, point);
        // 积分增加
        //TODO 获得本次交易增加的积分  暂时未除以1000
        int addPoint = order.getPaymentFee();
        addPointLog(order, point, addPoint);

        int resultPoint = point.getPoint() - order.getOperatePoint() + addPoint;
        point.setPoint(resultPoint);
        if (resultPoint < Constant.WX_POINT_LEVEL1) {
            point.setLevel(1);
            point.setLevelName("白银");
        } else if (resultPoint < Constant.WX_POINT_LEVEL2) {
            point.setLevel(2);
            point.setLevelName("黄金");
        } else if (resultPoint < Constant.WX_POINT_LEVEL3) {
            point.setLevel(3);
            point.setLevelName("铂金");
        } else {
            point.setLevel(4);
            point.setLevelName("钻石");
        }
        if (point.getId() == null) {
            wxPointMapper.add(point);
        } else {
            wxPointMapper.level(point);
        }
        return null;
    }

    /**
     * 增加积分
     *
     * @param order
     * @param point
     * @param addPoint
     */
    private void addPointLog(Order order, Point point, int addPoint) {
        PointLog pointLog = new PointLog();
        pointLog.setUserId(order.getUserId());
        pointLog.setAction(Constant.STATUS_ONE);
        pointLog.setOperatePoint(addPoint);
        int currentPoint = point.getPoint() - order.getOperatePoint() + addPoint;
        pointLog.setCurrentPoint(currentPoint);
        wxPointLogMapper.add(pointLog);
    }

    /**
     * 扣减积分
     *
     * @param order
     * @param point
     */
    private void subtractPointLog(Order order, Point point) {
        PointLog pointLog = new PointLog();
        pointLog.setUserId(order.getUserId());
        //扣减
        pointLog.setAction(Constant.STATUS_ZERO);
        //得到订单消耗的积分
        int operatePoint = order.getOperatePoint();
        int currentPoint = point.getPoint() - operatePoint;
        pointLog.setCurrentPoint(currentPoint);
        pointLog.setOperatePoint(-operatePoint);
        if (operatePoint != 0) {
            //不消耗积分，没有日志
            wxPointLogMapper.add(pointLog);
        }
    }

    /**
     * 构造订单
     *
     * @param goods              商品
     * @param goodSnapShotId     快照id
     * @param delivery           配送地址
     * @param wxOrderRequestData 下单参数
     * @param userId
     * @return
     */
    private Order constructOrder(Goods goods,
                                 Long goodSnapShotId,
                                 Delivery delivery,
                                 WxOrderRequestData wxOrderRequestData,
                                 String userId) {
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setGoodSnapShotId(goodSnapShotId);
        order.setPayType("");
        order.setOrderType("2");
        order.setOrderStatus("1");
        order.setGoodName(goods.getGoodName());
        order.setDeliveryId(delivery.getId());
        order.setOperatePoint(wxOrderRequestData.getPoint());
        order.setUserId(wxOrderRequestData.getUseTicket());
        order.setOrderFee(wxOrderRequestData.getOrderFee());
        order.setGoodFee(wxOrderRequestData.getGoodFee());
        order.setReduceFee(wxOrderRequestData.getReduceFee());
        order.setFreightFee(wxOrderRequestData.getFreightFee());
        order.setPaymentFee(wxOrderRequestData.getPaymentFee());
        order.setCount(wxOrderRequestData.getCount());
        order.setMessage(wxOrderRequestData.getMessage());
        order.setGoodId(goods.getId());
        if (Constant.STATUS_ONE.equals(wxOrderRequestData.getUseTicket())) {
            if (wxOrderRequestData.getTicket() != null) {
                order.setTicketId(wxOrderRequestData.getTicket());
            }
        }
        BeanUtils.copyProperties(delivery, order);
        order.setUserId(userId);
        return order;
    }

    /**
     * 构造商品快照
     *
     * @param goods
     * @return
     */
    private GoodSnapshot constructSnapshot(Goods goods) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goods.getId());
        goodSnapshot.setGoodDescription(goods.getDescription());
        return goodSnapshot;
    }

    /**
     * 构造优惠券快照
     *
     * @param ticket
     * @return
     */
    private TicketSnapshot constructTicketSnapshot(Ticket ticket) {
        TicketSnapshot snapshot = new TicketSnapshot();
        BeanUtils.copyProperties(ticket, snapshot);
        return snapshot;
    }

    /**
     * 预付款订单签名
     *
     * @param body       商品描述
     * @param attach
     * @param openid     用户openid
     * @param outTradeNo 商户订单号
     * @return
     */
    private String preOrderSend(String body,
                                String attach,
                                String openid,
                                String outTradeNo,
                                int totalFee,
                                String nonceStr) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("device_info", "10000");
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", totalFee);
        map.put("notify_url", WxPayConfig.NOTIFY_URL);
        map.put("trade_type", "JSAPI");
        String signTemp = WxPayUtil.sort(map);
        String sign = WxUtil.MD5(signTemp).toUpperCase();
        System.out.println("预付款Sign:" + sign);
        map.put("sign", sign);
        return WxPayUtil.mapConvertToXML(map);
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
     * 订单日志构造
     *
     * @param orderNo   订单no
     * @param action    动作
     * @param ipAddress IP地址
     * @param orderId   订单id
     * @return
     */
    private OrderLog constructOrderLog(String orderNo, String action, String ipAddress, Long orderId) {
        OrderLog orderLog = new OrderLog();
        orderLog.setAction(action);
        orderLog.setOrderNo(orderNo);
        orderLog.setIpAddress(ipAddress);
        orderLog.setOrderId(orderId);
        return orderLog;
    }

}
