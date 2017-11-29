package com.scoprion.mall.wx.service.activity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
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

    /**
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult groupList(int pageNo, int pageSize, String activity_type) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityType(activity_type);
        Date currentDate = new Date();
        //List<Page> pageList = new ArrayList<>();
        //活动进行中
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            Page<Activity> page = wxActivityMapper.groupList(activity_type);
        }
        //活动已结束
        if (activity.getEndDate().before(currentDate)) {
            Page<Activity> page = wxActivityMapper.groupList(activity_type);
        }
        //活动未开始
        if (activity.getStartDate().after(currentDate)) {
            Page<Activity> page = wxActivityMapper.groupList(activity_type);
        }
        Page<Activity> page = wxActivityMapper.groupList(activity_type);
        return new PageResult(page);
    }

    /**
     * 秒杀
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult secKill(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityTypeOne();
        Date currentDate = new Date();
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            //活动商品
            Page<Activity> page = wxActivityMapper.secKill();
            return new PageResult(page);
        }
        if (activity.getEndDate().before(currentDate)) {
            return null;
        }
        if (activity.getStartDate().after(currentDate)) {
            return null;
        }
        Page<Activity> page = wxActivityMapper.secKill();
        return new PageResult(page);
    }

    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult preference(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityTypeThree();
        Date currentDate = new Date();
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            //活动商品
            Page<Activity> page = wxActivityMapper.preference();
            return new PageResult(page);
        }
        if (activity.getEndDate().before(currentDate)) {
            return null;
        }
        if (activity.getStartDate().after(currentDate)) {
            return null;
        }
        Page<Activity> page = wxActivityMapper.preference();
        return new PageResult(page);
    }

    /**
     * 参加拼团
     *
     * @param orderExt
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult group(OrderExt orderExt, String ipAddress) {
        String openId = WxUtil.getOpenId(orderExt.getWxCode());
        //获得活动商品详情
        ActivityGoods activityGoods = wxActivityMapper.findByActivityGoodId(orderExt.getActivityGoodId());
        Long activityId = activityGoods.getActivityId();
        //查询是否参加过该活动
        int result = wxActivityMapper.validByActivityId(activityId, orderExt.getWxCode());
        if (result > 0) {
            return BaseResult.error("group_fail", "您已参加过该活动");
        }
        Date currentDate = new Date();
        //查询拼团详情
        Activity activity = wxActivityMapper.findById(activityId);
        if (activity.getNum() < 10) {
            return BaseResult.error("group_fail", "拼团人数不足");
        } else if (currentDate.after(activity.getEndDate())) {
            return BaseResult.error("group_fail", "拼团已过期");
        }


        //生成商品快照
        Long goodId = activityGoods.getGoodId();
        Goods goods = wxActivityMapper.findByGoodId(goodId);
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goodId);
        goodSnapshot.setGoodDescription(goods.getDescription());
        wxGoodSnapShotMapper.add(goodSnapshot);

        //生成预付款订单
        Order order = new Order();
        BeanUtils.copyProperties(orderExt.getDelivery(), order);
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(orderExt.getWxCode());
        order.setPayType("");
        order.setOrderType("3");
        order.setOrderStatus("1");
        order.setGoodName(goods.getGoodName());
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setGoodId(goodId);
        order.setGoodName(goods.getGoodName());
        order.setGoodFee(goods.getPrice() * activity.getDiscount());
        order.setDeliveryId(orderExt.getDelivery().getId());
        int orderResult = wxOrderMapper.add(order);
        if (orderResult <= 0) {
            return BaseResult.error("order_fail", "下单失败");
        }

        //系统内生成订单信息
        OrderLog orderLog = constructOrderLog(order.getOrderNo(), "生成试用订单", ipAddress, order.getId());
        wxOrderLogMapper.add(orderLog);
        //创建随机字符串
        String nonce_str = WxUtil.createRandom(false, 10);
        String xmlString = preOrderSend(goods.getGoodName(),
                "妆口袋",
                orderExt.getWxCode(),
                order.getOrderNo(),
                order.getFreightFee(),
                nonce_str);
        //生成预付款订单
        String wxOrderResponse = WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", xmlString);
        //将xml返回信息转换为bean
        UnifiedOrderResponseData unifiedOrderResponseData = WxPayUtil.castXMLStringToUnifiedOrderResponseData(
                wxOrderResponse);
        //修改订单预付款订单号
        wxOrderMapper.updateOrderForPrepayId(order.getId(), unifiedOrderResponseData.getPrepay_id());
        //时间戳
        Long timeStamp = System.currentTimeMillis() / 1000;
        //随机字符串
        String nonceStr = WxUtil.createRandom(false, 10);
        String paySign = paySign(timeStamp, nonceStr, unifiedOrderResponseData.getPrepay_id());
        unifiedOrderResponseData.setNonce_str(nonceStr);
        unifiedOrderResponseData.setPaySign(paySign);
        unifiedOrderResponseData.setTimeStamp(String.valueOf(timeStamp));
        return BaseResult.success(unifiedOrderResponseData);
    }

    public BaseResult callback(UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData) {
        Order order = wxOrderMapper.findByWxOrderNo(unifiedOrderNotifyRequestData.getOut_trade_no());
        //判断是否成功接收回调
        wxOrderMapper.updateOrderStatusAndPayStatusAndWxOrderNo(unifiedOrderNotifyRequestData.getTime_end(),
                unifiedOrderNotifyRequestData.getOut_trade_no(),
                unifiedOrderNotifyRequestData.getTransaction_id());
        if (null == order.getPayDate()) {
            //修改订单状态 以及微信订单号
            //记录订单日志
            OrderLog orderLog = constructOrderLog(unifiedOrderNotifyRequestData.getOut_trade_no(), "付款",
                    null, order.getId());
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
}
