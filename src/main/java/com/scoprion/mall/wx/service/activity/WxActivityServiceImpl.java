package com.scoprion.mall.wx.service.activity;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.exception.PayException;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.*;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.rabbitmq.SendGroupComponent;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * Created by fk on 2017/11/12.
 */
@SuppressWarnings("ALL")
@Service
public class WxActivityServiceImpl implements WxActivityService {

    @Autowired
    private WxActivityMapper wxActivityMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    @Autowired
    private WxGoodMapper wxGoodMapper;

    @Autowired
    private WxGoodSnapShotMapper wxGoodSnapShotMapper;

    @Autowired
    private WxFreeMapper wxFreeMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    @Autowired
    private SendGroupComponent sendGroupComponent;
    /**
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult groupList(int pageNo, int pageSize, String activityType) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Activity> page = wxActivityMapper.groupList(activityType);
        return new PageResult(page);
    }

    /**
     * 参加拼团
     *
     * @param orderExt
     * @param ipAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult joinGroup(WxGroupOrder wxGroupOrder, String ipAddress) {
        String openId = WxUtil.getOpenId(wxGroupOrder.getWxCode());

        //查询是否参加过该活动
        String activityMessage = checkActivity(wxGroupOrder.getGoodId(), wxGroupOrder.getActivityId(), openId);
        if (!StringUtils.isEmpty(activityMessage)) {
            return BaseResult.error("ERROR", activityMessage);
        }

        //获取收货地址
        Delivery delivery = wxDeliveryMapper.findById(wxGroupOrder.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("ERROR", "收货地址有误");
        }

        //生成商品快照
        Long goodId = wxGroupOrder.getGoodId();
        Goods goods = wxActivityMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = ServiceCommon.snapshotConstructor(goods, goodId);
        wxGoodSnapShotMapper.add(goodSnapshot);
        //sendGroupComponent.send(goodSnapshot);

        //组装订单信息
        Order order = orderConstructor(goods,goodSnapshot.getId(),delivery,wxGroupOrder,openId);
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("ERROR", "下单失败");
        }

        //系统内生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成试用订单", ipAddress, order.getId());
        wxOrderLogMapper.add(orderLog);

        //统一下单参数
        String nonce_str = WxUtil.createRandom(false, 10);
        String unifiedOrderXML = WxPayUtil.unifiedOrder(goods.getGoodName(),
                openId,
                order.getOrderNo(),
                order.getPaymentFee(),
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

        //写入参加活动记录
        UserActivity userActivity = new UserActivity();
        userActivity = userActivityConstructor(wxGroupOrder, openId);
        int userActivityResult = wxFreeMapper.add(userActivity);
        if (userActivityResult <= 0) {
            return BaseResult.error("ERROR", "添加活动记录失败");
        }

        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;

        //生成随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);

        //生成支付签名
        Map<String, Object> map = WxPayUtil.payParam(timeStamp, nonceStr, unifiedOrderResponseData.getPrepay_id());
        String paySign = WxPayUtil.paySign(map);
        map.put("paySign", paySign);
        return BaseResult.success(JSON.toJSON(map));
    }

    /**
     * 去支付
     *
     * @param wxCode
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult pay(Long orderId, Long activityId, Long goodId) {
        //查询订单详情
        Order order = wxOrderMapper.findByOrderId(orderId);

        String orderMessage = checkOrder(order);
        if (!StringUtils.isEmpty(orderMessage)) {
            return BaseResult.error("ERROR", "message");
        }
        //查询活动商品是否还有库存
        String activityGoodMessage = checkActivityGood(activityId, goodId);
        if (!StringUtils.isEmpty(activityGoodMessage)) {
            return BaseResult.error("ERROR", activityGoodMessage);
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
        orderLog.setOrderId(orderId);
        orderLog.setIpAddress(ipAddress);
        return orderLog;
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
        map.put("out_trade_no", outTradeNo);
        map.put("attach", attach);
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
        map.put("package", "prepay_id=" + prepayId);
        map.put("nonceStr", nonceStr);
        map.put("signType", "MD5");
        map.put("timeStamp", timeStamp);
        return WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
    }

    /**
     * 校验活动
     *
     * @param goodId
     * @param activityId
     * @param userId
     * @return
     */
    private String checkActivity(Long goodId, Long activityId, String userId) {

        //是否参加过活动
        int result = wxFreeMapper.validByActivityIdAndGoodIdAndUserId(activityId, userId, goodId);
        if (result > 0) {
            return "不能重复参加";
        }
        Date currentDate = new Date();
        //查询活动详情
        Activity activity = wxActivityMapper.findById(activityId);
        if (currentDate.after(activity.getEndDate())) {
            return "活动已过期";
        }
        if (currentDate.before(activity.getStartDate())) {
            return "活动未开始";
        }
        //判断活动商品库存
        ActivityGoods activityGoods = wxActivityMapper.findByActivityGoodStock(goodId);
        if (activityGoods.getStock() < 0) {
            return "商品库存不足";
        }
        //判断活动人数
        if (activity.getNum() > Constant.ACTIVITY_NUMBER) {
            return "活动人数已满";
        }
        return null;
    }

    /**
     * 订单组装
     *
     * @param delivery
     * @param snapshotId
     * @param userId
     * @param goods
     * @param wxFreeOrder
     * @return
     */

    private Order orderConstructor(Goods goods, Long snapshotId, Delivery delivery, WxGroupOrder wxGroupOrder, String userId) {
        Order order = new Order();
        BeanUtils.copyProperties(delivery, order);
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setPayType(CommonEnum.WE_CHAT_PAY.getCode());
        order.setOrderType(CommonEnum.SPELL_GROUP.getCode());
        order.setOrderStatus(CommonEnum.UN_PAY.getCode());
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(snapshotId);
        order.setGoodId(goods.getId());
        order.setGoodName(goods.getGoodName());
        order.setGoodFee(goods.getPrice());
        order.setUsePoint(CommonEnum.NOT_USE_POINT.getCode());
        order.setUseTicket(CommonEnum.UN_USE_TICKET.getCode());
        order.setFreightFee(wxGroupOrder.getFreightFee());
        order.setPaymentFee(wxGroupOrder.getPaymentFee());
        order.setDeliveryId(wxGroupOrder.getDeliveryId());
        order.setOrderFee(wxGroupOrder.getOrderFee());
        return order;
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
            return "订单状态异常";
        }
        long createTime = order.getCreateDate().getTime();
        long result = System.currentTimeMillis() - createTime;
        //超过两小时未支付订单  自动 关闭掉该订单
        if (result > Constant.TIME_HALF_HOUR) {
            //关闭订单
            wxOrderMapper.updateByOrderID(order.getId(), CommonEnum.CLOSING.getCode());
            return "订单已超时，请重新下单";
        }
        return null;
    }

    /**
     * 校验活动库存
     *
     * @param activityId
     * @param goodId
     * @return
     */
    private String checkActivityGood(Long activityId, Long goodId) {
        //判断活动商品库存
        ActivityGoods activityGoods = wxActivityMapper.findByActivityIdAndGoodId(activityId, goodId);
        if (activityGoods.getStock() <= 0 || null == activityGoods) {
            return "库存不足";
        }
        if (CommonEnum.UN_NORMAL.getCode().equals(activityGoods.getStatus())) {
            return "活动商品信息已过期,请重新下单";
        }
        if (CommonEnum.OFF_SALE.getCode().equals(activityGoods.getOnSale())) {
            return "活动商品下架,请重新下单";
        }
        return activityGoods.toString();
    }

    /**
     * 组装参加活动记录
     *
     * @param WxGroupOrder
     * @param openId
     * @return
     */
    public static UserActivity userActivityConstructor(WxGroupOrder wxGroupOrder, String openId) {
        UserActivity userActivity = new UserActivity();
        userActivity.setActivityId(wxGroupOrder.getActivityId());
        userActivity.setUserId(openId);
        userActivity.setGoodId(wxGroupOrder.getActivityId());
        return userActivity;
    }
}
