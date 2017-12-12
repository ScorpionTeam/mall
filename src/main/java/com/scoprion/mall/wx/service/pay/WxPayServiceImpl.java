package com.scoprion.mall.wx.service.pay;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.exception.PayException;
import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.domain.good.GoodLog;
import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderLog;
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
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/6.
 */
@SuppressWarnings("ALL")
@Service
public class WxPayServiceImpl implements WxPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    private WxGoodMapper wxGoodMapper;

    @Autowired
    private GoodLogMapper goodLogMapper;

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


    /**
     * 统一下单
     *
     * @param wxOrderRequestData
     * @param wxCode
     * @param ipAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult unifiedOrder(WxOrderRequestData wxOrderRequestData) {
        //查询用户openid
        String openid = WxUtil.getOpenId(wxOrderRequestData.getWxCode());
        //积分判断
        BaseResult x = checkPoint(wxOrderRequestData, openid);
        if (x != null) {
            return x;
        }
        //校验优惠券
        String ticketMessage = checkAndUseTicket(wxOrderRequestData.getUseTicket(), wxOrderRequestData.getTicket());
        if (!StringUtils.isEmpty(ticketMessage)) {
            return BaseResult.error("error", ticketMessage);
        }

        //查询商品库存
        Goods goods = wxGoodMapper.findById(wxOrderRequestData.getGoodId());
        //商品信息校验
        String goodMessage = checkGood(goods, wxOrderRequestData.getOrderFee(),
                wxOrderRequestData.getCount());
        if (!StringUtils.isEmpty(goodMessage)) {
            return BaseResult.error("ERROR", goodMessage);
        }
        //查询收货地址
        Delivery delivery = wxDeliveryMapper.findById(wxOrderRequestData.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("ERROR", "收货地址有误");
        }

        //创建商品快照
        GoodSnapshot goodSnapshot = constructSnapshot(goods);
        wxGoodSnapShotMapper.add(goodSnapshot);

        //组装订单信息
        Order order = constructOrder(goods, goodSnapshot.getId(), delivery, wxOrderRequestData, openid);
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("ERROR", "下单出错");
        }

        //系统内部生成订单信息
//        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成预付款订单", wxOrderRequestData.getIpAddress(), order.getId());
//        wxOrderLogMapper.add(orderLog);
        ServiceCommon.saveWxOrderLog(order.getId(), wxOrderRequestData.getIpAddress(), order.getOrderNo(),
                "生成预付款订单", wxOrderLogMapper);


        String nonce_str = WxUtil.createRandom(false, 10);
        //统一下单参数调用
        String unifiedOrderXML = WxPayUtil.unifiedOrder(goods.getGoodName(),
                openid,
                order.getOrderNo(),
                wxOrderRequestData.getPaymentFee(),
                nonce_str);
        //生成预付款订单
        String wxOrderResponse = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", unifiedOrderXML);
        //将xml返回信息转换为bean
        UnifiedOrderResponseData unifiedOrderResponseData = WxPayUtil.castXMLStringToUnifiedOrderResponseData(
                wxOrderResponse);

        if (unifiedOrderResponseData.getReturn_code().equalsIgnoreCase("FAIL")) {
            throw new PayException(unifiedOrderResponseData.getReturn_msg());
        }
        //修改订单预付款订单号
        wxOrderMapper.updateOrderForPrepayId(order.getId(), unifiedOrderResponseData.getPrepay_id());

        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;

        //随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);

        //生成支付签名
        Map<String, Object> map = WxPayUtil.payParam(timeStamp, nonceStr, unifiedOrderResponseData.getPrepay_id());
        String paySign = WxPayUtil.paySign(map);
        map.put("paySign", paySign);
        return BaseResult.success(JSON.toJSON(map));
    }


    /**
     * 重新发起支付
     *
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult pay(Long orderId) {
        Order order = wxOrderMapper.findByOrderId(orderId);

        //校验订单信息
        String orderMessage = checkOrder(order);
        if (!StringUtils.isEmpty(orderMessage)) {
            return BaseResult.error("ERROR", orderMessage);
        }
        Goods goods = wxGoodMapper.findById(order.getGoodId());

        //校验商品信息
        String goodMessage = checkGood(goods, 0, 0);
        if (!StringUtils.isEmpty(goodMessage)) {
            return BaseResult.error("ERROR", goodMessage);
        }

        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;
        //随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);
        Map<String, Object> map = WxPayUtil.payParam(timeStamp, nonceStr, order.getPrepayId());
        String paySign = WxPayUtil.paySign(map);
        map.put("paySign", paySign);
        return BaseResult.success(JSON.toJSON(map));
    }


    /**
     * 支付成功回调
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData) {
        Order order = wxOrderMapper.findByWxOrderNo(unifiedOrderNotifyRequestData.getOut_trade_no());
        if (order == null) {
            LOGGER.info("订单为空，查询不到订单信息");
            return BaseResult.success("订单信息查询出错");
        }
        //判断是否成功接收回调
        if (order != null && null == order.getPayDate()) {
            //修改订单状态 以及微信订单号
            wxOrderMapper.updateOrderStatusAndPayStatusAndWxOrderNo(unifiedOrderNotifyRequestData.getTime_end(),
                    unifiedOrderNotifyRequestData.getOut_trade_no(),
                    unifiedOrderNotifyRequestData.getTransaction_id());
            //记录订单日志
//            OrderLog orderLog = constructOrderLog(unifiedOrderNotifyRequestData.getOut_trade_no(), "付款",
//                    null, order.getId());
//            wxOrderLogMapper.add(orderLog);
            ServiceCommon.saveWxOrderLog(order.getId(), null, order.getOrderNo(), "付款", wxOrderLogMapper);

            //库存扣减
            wxGoodMapper.updateGoodStockById(order.getGoodId(), order.getCount());
            //库存扣减日志
            saveGoodLog(order.getGoodId(), "库存扣减" + order.getCount(), order.getGoodName());
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
     * 检查优惠券&使用优惠券
     *
     * @param useTicket
     * @param ticketId
     * @return
     */
    private String checkAndUseTicket(String useTicket, Long ticketId) {
        //使用优惠券
        if (CommonEnum.USE_TICKET.getCode().equals(useTicket)) {
            TicketSnapshot ticketSnapshot = wxTicketSnapshotMapper.findByUserIdAndTicketId(
                    ticketId);
            if (ticketSnapshot == null) {
                return "请先领取优惠券";
            }
            if (ticketSnapshot.getStartDate().after(new Date())) {
                return "优惠券未到使用日期";
            }
            if (CommonEnum.UN_NORMAL.getCode().equals(ticketSnapshot.getStatus())) {
                return "优惠券已经使用过了";
            }
            if (ticketSnapshot.getEndDate().before(new Date())) {
                return "优惠券已过期";
            }
            //优惠券状态改为已使用
            wxTicketSnapshotMapper.modifyStatus(CommonEnum.UN_NORMAL.getCode(), ticketSnapshot.getId());
            return null;
        }
        return null;
    }

    /**
     * 下单检查商品信息
     *
     * @param goods    商品
     * @param orderFee 订单金额
     * @param count    购买数量
     * @return
     */
    private String checkGood(Goods goods, int orderFee, int count) {
        //查询商品库存
        if (null == goods || goods.getStock() <= 0) {
            return "商品库存不足";
        }
        if (CommonEnum.OFF_SALE.getCode().equals(goods.getOnSale())) {
            //商品处于下架状态，不能下单
            return "商品已下架";
        }
        //价格判断
        if (orderFee != 0 && count != 0) {
            int unitPrice = orderFee / count;
            if (goods.getPrice() != unitPrice) {
                return "商品信息已过期，请重新下单";
            }
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
                return BaseResult.error("ERROR", "下单失败，没有可使用的积分");
            }
        } else if (wxOrderRequestData.getPoint() > point.getPoint()) {
            //有积分，使用量超过已有积分
            return BaseResult.error("ERROR", "下单失败,积分不足");
        }
        return null;
    }


    /**
     * 校验订单信息
     *
     * @param order
     * @return
     */
    private String checkOrder(Order order) {
        if (order == null) {
            return "找不到订单";
        }
        if (!CommonEnum.UN_PAY.getCode().equals(order.getOrderStatus())) {
            return "订单状态异常了";
        }
        long createTime = order.getCreateDate().getTime();
        long result = System.currentTimeMillis() - createTime;
        //超过两小时未支付订单  自动 关闭掉该订单
        if (result > Constant.TIME_TWO_HOUR) {
            //关闭订单
            wxOrderMapper.updateByOrderID(order.getId(), CommonEnum.CLOSING.getCode());
            return "订单已超时，请重新下单";
        }
        return null;
    }


    /**
     * 积分操作
     *
     * @param order
     * @return
     */
    private BaseResult operatePoint(Order order) {
        Point point = wxPointMapper.findByUserId(order.getUserId());
        //第一次发起购买行为
        if (point == null) {
            point = new Point();
        } else {
            //非第一次购买
            if (CommonEnum.USE_POINT.getCode()
                    .equals(order.getUsePoint()) && order.getOperatePoint() > point.getPoint()) {
                return BaseResult.error("ERROR", "支付失败积分不足");
            }

        }

        //积分扣减日志
        subtractPointLog(order, point.getPoint());
        //TODO 获得本次交易增加的积分  暂时未除以1000
//        int addPoint = order.getPaymentFee()/1000;
        int addPoint = order.getPaymentFee();
        int currentPoint = point.getPoint() - order.getOperatePoint() + addPoint;
        // 积分增加日志
        addPointLog(order, currentPoint, addPoint);

        point.setPoint(currentPoint);
        point.setUserId(order.getUserId());
        if (currentPoint < Constant.WX_POINT_LEVEL1) {
            point.setLevel(1);
            point.setLevelName("白银");
        } else if (currentPoint < Constant.WX_POINT_LEVEL2) {
            point.setLevel(2);
            point.setLevelName("黄金");
        } else if (currentPoint < Constant.WX_POINT_LEVEL3) {
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
        LOGGER.info("积分操作：" + point.toString());
        return null;
    }

    /**
     * 保存商品日志
     *
     * @param goodId
     * @param action
     * @param goodName
     */
    private void saveGoodLog(Long goodId, String action, String goodName) {
        ServiceCommon.saveGoodLog(goodName, action, goodId, goodLogMapper);
    }

    /**
     * 增加积分
     *
     * @param order
     * @param currPoint
     * @param addPoint
     */
    private void addPointLog(Order order, int currPoint, int addPoint) {
        PointLog pointLog = new PointLog();
        pointLog.setUserId(order.getUserId());
        pointLog.setAction(CommonEnum.PRODUCE_POINT.getCode());
        pointLog.setOperatePoint(addPoint);
        pointLog.setCurrentPoint(currPoint);
        wxPointLogMapper.add(pointLog);
    }

    /**
     * 扣减积分
     *
     * @param order
     * @param currPoint
     */
    private void subtractPointLog(Order order, Integer currPoint) {
        if (CommonEnum.USE_POINT.getCode().equals(order.getUsePoint())) {
            PointLog pointLog = new PointLog();
            pointLog.setUserId(order.getUserId());
            //扣减
            pointLog.setAction(CommonEnum.CONSUME_POINT.getCode());
            //得到订单消耗的积分
            int operatePoint = order.getOperatePoint();
            int currentPoint = currPoint - operatePoint;
            pointLog.setCurrentPoint(currentPoint);
            pointLog.setOperatePoint(-operatePoint);
            if (operatePoint != 0) {
                //不消耗积分，没有日志
                wxPointLogMapper.add(pointLog);
            }
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
        order.setSellerId(goods.getSellerId());
        order.setGoodSnapShotId(goodSnapShotId);
        order.setPayType("");
        order.setOrderType(CommonEnum.MOBILE_ORDER.getCode());
        order.setOrderStatus(CommonEnum.UN_PAY.getCode());
        order.setGoodName(goods.getGoodName());
        order.setDeliveryId(delivery.getId());
        order.setOperatePoint(wxOrderRequestData.getPoint());
        order.setUseTicket(wxOrderRequestData.getUseTicket());
        order.setOrderFee(wxOrderRequestData.getOrderFee());
        order.setGoodFee(wxOrderRequestData.getGoodFee());
        order.setReduceFee(wxOrderRequestData.getReduceFee());
        order.setFreightFee(wxOrderRequestData.getFreightFee());
        order.setPaymentFee(wxOrderRequestData.getPaymentFee());
        order.setCount(wxOrderRequestData.getCount());
        order.setMessage(wxOrderRequestData.getMessage());
        order.setGoodId(goods.getId());
        order.setSellerId(goods.getSellerId());
        //是否使用优惠券
        if (CommonEnum.USE_TICKET.getCode().equals(wxOrderRequestData.getUseTicket())) {
            order.setUseTicket(CommonEnum.USE_TICKET.getCode());
            if (wxOrderRequestData.getTicket() != null) {
                order.setTicketId(wxOrderRequestData.getTicket());
            }
        } else {
            order.setUseTicket(CommonEnum.UN_USE_TICKET.getCode());
        }
        //是否使用积分
        if (wxOrderRequestData.getPoint() > 0) {
            order.setUsePoint(CommonEnum.USE_POINT.getCode());
        } else {
            order.setUsePoint(CommonEnum.NOT_USE_POINT.getCode());
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
