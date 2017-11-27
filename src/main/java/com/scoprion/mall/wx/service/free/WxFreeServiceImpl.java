package com.scoprion.mall.wx.service.free;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.*;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
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
    @Override
    public BaseResult apply(OrderExt orderExt, String ipAddress) {
        String openId = WxUtil.getOpenId(orderExt.getWxCode());
        //获得活动商品详情
        ActivityGoods activityGoods = wxFreeMapper.findByActivityGoodId(orderExt.getActivityGoodId());
        Long activityId = activityGoods.getActivityId();
        //查询是否参加过该活动
        int result = wxFreeMapper.validByActivityId(activityId, openId);
        if (result > 0) {
            return BaseResult.error("apply_fail", "您已参加过该活动");
        }
        Date currentDate = new Date();
        //查询活动详情
        Activity activity = wxFreeMapper.findById(activityId);
        if (0 == activity.getNum()) {
            return BaseResult.error("apply_fail", "活动人数已满");
        } else if (currentDate.after(activity.getEndDate())) {
            return BaseResult.error("apply_fail", "活动已过期");
        }

        //生成商品快照
        Long goodId = activityGoods.getGoodId();
        Goods goods = wxFreeMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);

        //生成预付款订单
        Order order = new Order();
        BeanUtils.copyProperties(orderExt.getDelivery(), order);
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(openId);
        order.setPayType("");
        order.setOrderType("3");
        order.setOrderStatus("1");
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setGoodId(goodId);
        order.setGoodName(goods.getGoodName());
        order.setGoodFee(goods.getPrice());
        order.setDeliveryId(orderExt.getDelivery().getId());
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("order_fail", "下单失败");
        }

        //系统内生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成试用订单", ipAddress);
        wxOrderLogMapper.add(orderLog);
        //创建随机字符串
        String nonce_str = WxUtil.createRandom(false, 10);
        String xmlString = preOrderSend(goods.getGoodName(),
                "妆口袋",
                openId,
                order.getOrderNo(),
                order.getFreightFee(),
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
     * 支付
     *
     * @param wxCode
     * @param orderId
     * @return
     */
    @Override
    public BaseResult pay(String wxCode, Long orderId) {
        String openId = WxUtil.getOpenId(wxCode);
        //查询订单详情
        Order order = wxOrderMapper.findByOrderId(orderId);
        if (StringUtils.isEmpty(openId)) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(order.toString())) {
            return BaseResult.notFound();
        }
        if (!Constant.STATUS_ONE.equals(order.getOrderStatus())) {
            return BaseResult.error("order_fail", "订单错误");
        }
        //获取订单创建的时间
        long createTime = order.getCreateDate().getTime();
        long timeResult = System.currentTimeMillis() - createTime;
        if (timeResult > Constant.TIME_TWO_HOUR) {
            return BaseResult.error("order_fail", "您的订单已超时,请重新下单");
        }
        //查询商品库存
        Goods goods = wxGoodMapper.findById(order.getGoodId());
        if (null == goods || goods.getStock() <= 0) {
            return BaseResult.error("not_enough_stock", "商品库存不足");
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            return BaseResult.error("not_enough_stock", "商品已经下架");
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            //商品处于下架状态，不能下单
            return BaseResult.error("can_not_order", "商品已下架");
        }

        //根据openid查询用户订单信息
        String prepayId = wxOrderMapper.findPrepayIdByOpenid(openId, orderId);
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

    @Override
    public BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData) {
        Order order = wxOrderMapper.findByWxOrderNo(unifiedOrderNotifyRequestData.getOut_trade_no());
//        //判断签名是否被篡改
//        String sign = unifiedOrderNotifyRequestData.getSign();
//        System.out.println("回调返回Sign:" + sign);
//        String nonce_str = unifiedOrderNotifyRequestData.getNonce_str();
//        BigDecimal fee = order.getTotalFee().multiply(new BigDecimal(100));
//        int totalFee = fee.intValue() / 100;
//        String localSign = preOrderSend(order.getGoodName(),
//                "妆口袋",
//                unifiedOrderNotifyRequestData.getOpenid(),
//                order.getOrderNo(),
//                totalFee,
//                nonce_str);
//        System.out.println("本地再签:" + localSign);
        //判断是否成功接收回调
        wxOrderMapper.updateOrderStatusAndPayStatus(unifiedOrderNotifyRequestData.getTime_end(),
                unifiedOrderNotifyRequestData.getOut_trade_no(),
                unifiedOrderNotifyRequestData.getTransaction_id());
        if (null == order.getPayDate()) {
            //修改订单状态 以及微信订单号
            //记录订单日志
            OrderLog orderLog = constructOrderLog(unifiedOrderNotifyRequestData.getOut_trade_no(), "付款", null);
            wxOrderLogMapper.add(orderLog);
            //库存扣减
            wxGoodMapper.updateGoodStockById(order.getGoodId(), order.getCount());
//            //积分扣减、增加
//            BaseResult operateResult = operatePoint(order);
//            if (operateResult != null) {
//                return operateResult;
//            }
            //销量
            wxGoodMapper.updateSaleVolume(order.getCount(), order.getGoodId());
        }
        return BaseResult.success("支付回调成功");
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
        orderLog.setOrderNo(orderNo);
        orderLog.setAction(action);
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
}
