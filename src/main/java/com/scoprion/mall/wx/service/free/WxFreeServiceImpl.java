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
import com.scoprion.mall.domain.good.GoodLog;
import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderLog;
import com.scoprion.mall.wx.mapper.*;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.service.activity.WxActivityServiceImpl;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(WxFreeServiceImpl.class);

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
     * @param wxFreeOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult apply(WxFreeOrder wxFreeOrder) {
        String userId = WxUtil.getOpenId(wxFreeOrder.getWxCode());

        //查询是否参加过该活动
        String activityMessage = checkActivity(wxFreeOrder.getGoodId(), wxFreeOrder.getActivityId(), userId);
        if (!StringUtils.isEmpty(activityMessage)) {
            return BaseResult.error("ERROR", activityMessage);
        }

        //查询活动商品是否还有库存
        String activityGoodMessage = checkActivityGood(wxFreeOrder.getGoodId());
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
//        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成试用订单", wxFreeOrder.getIpAddress(), order.getId());
//        wxOrderLogMapper.add(orderLog);
        ServiceCommon.saveWxOrderLog(order.getId(), wxFreeOrder.getIpAddress(), order.getOrderNo(),
                "生成试用订单", wxOrderLogMapper);
        //统一下单参数
        String nonce_str = WxUtil.createRandom(false, 10);
        String unifiedOrderXML = WxPayUtil.freeOrder(goods.getGoodName(),
                userId,
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
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult pay(Long orderId) {
        //查询订单详情
        Order order = wxOrderMapper.findByOrderId(orderId);

        //订单信息校验
        String orderMessage = checkOrder(order);
        if (!StringUtils.isEmpty(orderMessage)) {
            return BaseResult.error("ERROR", orderMessage);
        }

        //查询活动商品信息
        String activityGoodMessage = checkActivityGood(order.getGoodId());
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
     * 接收微信回调(免费试用)
     *
     * @param unifiedOrderNotifyRequestData
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult callBack(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData) {
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
            ServiceCommon.saveWxOrderLog(order.getId(), null, order.getOrderNo(), "付款", wxOrderLogMapper);
            //取到订单里的商品id
            ActivityGoods activityGoods = wxActivityMapper.findByActivityGoodStock(order.getGoodId());
            //库存扣减
            wxGoodMapper.updateActivityGoodStockById(activityGoods.getId(), order.getCount());
            //库存扣减日志
            saveGoodLog(order.getGoodId(), "库存扣减" + order.getCount(), order.getGoodName());
            //销量
            wxGoodMapper.updateSaleVolume(order.getCount(), order.getGoodId());
        }
        return BaseResult.success("支付回调成功");
    }


    /**
     * 记录商品日志
     *
     * @param order
     */
    private void saveGoodLog(Long goodId, String action, String goodName) {
//        GoodLog goodLog = new GoodLog();
//        goodLog.setAction("库存扣减" + order.getCount());
//        goodLog.setGoodName(order.getGoodName());
//        goodLog.setGoodId(order.getGoodId());
//        goodLogMapper.add(goodLog);
        ServiceCommon.saveGoodLog(goodName, action, goodId, goodLogMapper);
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
        order.setSellerId(goods.getSellerId());
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
        order.setPaymentFee(wxFreeOrder.getPaymentFee());
        order.setDeliveryId(wxFreeOrder.getDeliveryId());
        order.setCount(1);
        order.setSellerId(goods.getSellerId());
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
    private String checkActivityGood(Long goodId) {
        //判断活动商品库存
        ActivityGoods activityGoods = wxFreeMapper.findByActivityIdAndGoodId(goodId);
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


}
