package com.scoprion.mall.wx.service.free;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.exception.PayException;
import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.*;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author by hmy
 * @created on 2017/11/20.
 */
@SuppressWarnings("ALL")
@Service
public class WxFreeServiceImpl implements WxFreeService {

    @Autowired
    private WxFreeMapper wxFreeMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Autowired
    private WxOrderLogMapper wxOrderLogMapper;

    @Autowired
    private WxActivityMapper wxActivityMapper;

    @Autowired
    private WxGoodMapper wxGoodMapper;

    @Autowired
    private GoodLogMapper goodLogMapper;

    @Autowired
    private WxGoodSnapShotMapper wxGoodSnapShotMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Activity> page = wxActivityMapper.findAll();
        return new PageResult(page);
    }

    /**
     * 参加试用活动
     *
     * @param orderExt  订单
     * @param ipAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult apply(WxFreeOrder wxFreeOrder, String ipAddress) {
        String userId = WxUtil.getOpenId(wxFreeOrder.getWxCode());

        //查询是否参加过该活动
        String activityMessage = checkActivity(wxFreeOrder.getGoodId(), wxFreeOrder.getActivityId(), userId);
        if (!StringUtils.isEmpty(activityMessage)) {
            return BaseResult.error("ERROR", activityMessage);
        }

        //查询活动商品是否还有库存
        String activityGoodMessage = checkActivityGood(wxFreeOrder.getActivityId(), wxFreeOrder.getGoodId());
        if (!StringUtils.isEmpty(activityGoodMessage)) {
            return BaseResult.error("ERROR", activityGoodMessage);
        }
        //获取收货地址
        Delivery delivery = wxDeliveryMapper.findById(wxFreeOrder.getDeliveryId());
        if (null == delivery) {
            return BaseResult.error("ERROR", "收货地址有误");
        }
        //生成商品快照
        Long goodId = wxFreeOrder.getGoodId();
        Goods goods = wxGoodMapper.findById(goodId);
        GoodSnapshot goodSnapshot = ServiceCommon.snapshotConstructor(goods, goodId);
        wxGoodSnapShotMapper.add(goodSnapshot);

        //组装订单信息
        Order order = orderConstructor(delivery, goodSnapshot.getId(), userId, goods, wxFreeOrder);
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
                userId,
                order.getOrderNo(),
                order.getFreightFee(),
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

        //添加活动记录
        UserActivity userActivity = ServiceCommon.userActivityConstructor(wxFreeOrder, userId);
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
     * @param orderId
     * @param activityId
     * @param goodId
     * @return
     */
    @Override
    public BaseResult pay(Long orderId, Long activityId, Long goodId) {
        //查询订单详情
        Order order = wxOrderMapper.findByOrderId(orderId);
        //订单信息校验
        String orderMessage = checkOrder(order);
        if (!StringUtils.isEmpty(orderMessage)) {
            return BaseResult.error("ERROR", orderMessage);
        }
        long createTime = order.getCreateDate().getTime();
        long result = System.currentTimeMillis() - createTime;
        //超过两小时未支付订单  自动 关闭掉该订单
        if (result > Constant.TIME_TWO_HOUR) {
            //关闭订单
            wxOrderMapper.updateByOrderID(order.getId(), CommonEnum.CLOSING.getCode());
            return BaseResult.error("ERROR", "订单已超时，请重新下单");
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
     * 记录商品日志
     *
     * @param order
     */
    private void saveGoodLog(Order order) {
        GoodLog goodLog = new GoodLog();
        goodLog.setAction("库存扣减" + order.getCount());
        goodLog.setGoodName(order.getGoodName());
        goodLog.setGoodId(order.getGoodId());
        goodLogMapper.add(goodLog);
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
        orderLog.setOrderNo(orderNo);
        orderLog.setAction(action);
        orderLog.setOrderId(orderId);
        orderLog.setIpAddress(ipAddress);
        return orderLog;
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
    private Order orderConstructor(Delivery delivery, Long snapshotId, String userId, Goods goods, WxFreeOrder wxFreeOrder) {
        Order order = new Order();
        BeanUtils.copyProperties(delivery, order);
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setPayType(CommonEnum.WE_CHAT_PAY.getCode());
        order.setOrderType(CommonEnum.FREE_ORDER.getCode());
        order.setOrderStatus(CommonEnum.UN_PAY.getCode());
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(snapshotId);
        order.setGoodId(goods.getId());
        order.setGoodName(goods.getGoodName());
        order.setUsePoint(CommonEnum.NOT_USE_POINT.getCode());
        order.setUseTicket(CommonEnum.UN_USE_TICKET.getCode());
        order.setFreightFee(wxFreeOrder.getFreightFee());
        order.setDeliveryId(wxFreeOrder.getDeliveryId());
        order.setUserId(wxFreeOrder.getWxCode());
        return order;
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
        Activity activity = wxFreeMapper.findById(activityId);
        if (currentDate.after(activity.getEndDate())) {
            return "活动已过期";
        }
        if (currentDate.before(activity.getStartDate())) {
            return "活动未开始";
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
        ActivityGoods activityGoods = wxFreeMapper.findByActivityIdAndGoodId(activityId, goodId);
        if (activityGoods.getStock() <= 0 || null == activityGoods) {
            return "库存不足";
        }
        if (CommonEnum.UN_NORMAL.getCode().equals(activityGoods.getStatus())) {
            return "活动商品信息已过期,请重新下单";
        }
        if (CommonEnum.OFF_SALE.getCode().equals(activityGoods.getOnSale())) {
            return "活动商品下架,请重新下单";
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
            return "订单状态异常";
        }
        return null;
    }


}
