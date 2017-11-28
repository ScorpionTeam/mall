package com.scoprion.mall.backstage.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.*;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/9/29.
 *
 * @author ycj
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodLogMapper goodLogMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private WxPointMapper wxPointMapper;

    @Autowired
    private WxPointLogMapper wxPointLogMapper;

    @Autowired
    private SendGoodMapper sendGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;


    /**
     * 退货
     *
     * @return
     */
    @Override
    public BaseResult goodReject(Long orderId, Integer count) {
        Order order = orderMapper.findById(orderId);
        //返回库存--商品规则返库
        goodsMapper.modifyGoodsDeduction(order.getGoodId(), count);
        //关闭该订单
        order.setOrderStatus("6");
        orderMapper.modify(order);
        saveOrderLog(orderId, order.getOrderNo(), "退货");
        saveGoodLog(order.getGoodName(), order.getGoodId(), "订单退货,返回库存");
        return BaseResult.success("操作成功");
    }

    /**
     * 商品发货
     *
     * @param orderId     订单号
     * @param deliveryNo  运单号
     * @param expressName 快递公司
     * @param expressNo   快递公司编号
     * @param senderId    寄件人Id
     * @return
     */
    @Override
    public BaseResult sendGood(Long orderId, String deliveryNo, String expressName, String expressNo, Long senderId) {
        if (orderId == null) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(deliveryNo)) {
            return BaseResult.error("delivery_error", "运单号不能为空");
        }
        if (StringUtils.isEmpty(expressName)) {
            return BaseResult.error("express_error", "快递公司不能为空");
        }
        senderId = 12L;
        if (senderId == null) {
            return BaseResult.error("sender_error", "发件人id不能为空");
        }
        Order order = orderMapper.findById(orderId);
        //生成发货信息记录，
        SendGood sendGood = saveSendGoodInfo(orderId, deliveryNo, expressName, expressNo, senderId);
        //修改订单信息，发货时间、状态3(待收货)，发货信息id，
        orderMapper.updateSendGood(orderId, 3, deliveryNo, sendGood.getId());
        //生成订单日志
        saveOrderLog(orderId, order.getOrderNo(), "订单发货");
        return BaseResult.success("操作成功");
    }

    private SendGood saveSendGoodInfo(Long orderId, String deliveryNo, String expressName, String expressNo, Long senderId) {
        SendGood sendGood = new SendGood();
        sendGood.setDeliveryNo(deliveryNo);
        sendGood.setExpressName(expressName);
        sendGood.setExpressNo(expressNo);
        sendGood.setOrderId(orderId);
        sendGood.setSenderId(senderId);
        sendGoodMapper.add(sendGood);
        return sendGood;
    }

    @Override
    public PageResult findOrderLogByOrderId(Integer pageNo, Integer pageSize, Long orderId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<OrderLog> pages = orderLogMapper.findByOrderId(orderId);
        return new PageResult(pages);
    }

    /**
     * 订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param payType     支付类型0 支付宝1 微信2 信用卡3 储蓄卡
     * @param orderType   订单类型 1pc订单  2手机订单
     * @param orderStatus 状态  0 全部  1 待付款   2 待发货  3 待收货 4 已完成
     * @param searchKey   模糊查询信息
     * @param startDate   开始时间
     * @param endDate     结束时间
     * @param phone       收件人手机号
     * @param orderNo     订单号
     * @return
     */
    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String payType, String orderType,
                                      String orderStatus, String searchKey, String startDate, String endDate,
                                      String phone, String orderNo) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(startDate)) {
            startDate = null;
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = null;
        }
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<OrderExt> orderPage = orderMapper.findByCondition(payType, orderType, orderStatus, searchKey, startDate,
                endDate, phone, orderNo);
        if (orderPage == null) {
            return new PageResult();
        }
        return new PageResult(orderPage);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        OrderExt orderExt = orderMapper.findById(id);
        if (orderExt == null) {
            return BaseResult.notFound();
        }
        SendGood sendGood = sendGoodMapper.findById(orderExt.getSendGoodId());
        orderExt.setSendGood(sendGood);
        Delivery delivery = wxDeliveryMapper.findById(orderExt.getDeliveryId());
        orderExt.setDelivery(delivery);
        return BaseResult.success(orderExt);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @Override
    public BaseResult modify(Order order) {
        if (order.getId() == null) {
            return BaseResult.parameterError();
        }
        Order localOrder = orderMapper.findById(order.getId());
        if (CommonEnum.UN_PAY.getCode().equals(localOrder.getOrderStatus())) {
            return BaseResult.error("modify_error", "未付款的订单不能修改");
        }
        orderMapper.modify(order);
        saveOrderLog(order.getId(), order.getOrderNo(), "修改订单");
        return BaseResult.success("修改成功");
    }

    /**
     * 退款
     *
     * @param orderId   订单id
     * @param flag      标识 0 拒绝  1 通过
     * @param remark    退款备注
     * @param refundFee 退款金额
     * @return
     */
    @Override
    public BaseResult refund(Long orderId, String flag, String remark, int refundFee) throws Exception {
        if ("0".equals(flag)) {
            orderMapper.updateOrderRefundById(orderId, "7", remark);
            return BaseResult.success("审核完成");
        } else {
            String nonceStr = WxUtil.createRandom(false, 10);
            Order order = orderMapper.findById(orderId);
            String refundOrderNo = order.getOrderNo() + "T";
            //定义接收退款返回字符串
            String refundXML = refundSign(order.getOrderNo(), order.getPaymentFee(), refundFee, refundOrderNo,
                    nonceStr);
            //接收退款返回
            String response = WxPayUtil.doRefund(WxPayConfig.WECHAT_REFUND, refundXML, WxPayConfig.MCHID);
            WxRefundNotifyResponseData wxRefundNotifyResponseData = WxPayUtil.castXMLStringToWxRefundNotifyResponseData(
                    response);
            Boolean result = "success".equalsIgnoreCase(wxRefundNotifyResponseData.getReturn_code());
            if (result) {
                orderMapper.updateOrderRefundById(order.getId(), "6", "");
                saveOrderLog(order.getId(), order.getOrderNo(), "退款");
                goodsMapper.updateGoodStockById(order.getGoodId(), order.getCount());
                //记录商品库存反还日志
                saveGoodLog(order.getGoodName(), order.getGoodId(), "退款，商品库存返还");
                //使用积分返还
                //支付时扣减的积分，需要返还
                int operatePoint = order.getOperatePoint();
                Point localPoint = pointMapper.findByUserId(order.getUserId());
                if (CommonEnum.USE_POINT.getCode().equals(order.getUsePoint())) {
                    //积分返还
                    pointMapper.updatePoint(order.getUserId(), operatePoint);
                    //记录积分返还日志
                    savePointLog(order, "1", +operatePoint, localPoint.getPoint() + operatePoint);
                    localPoint.setPoint(localPoint.getPoint() + operatePoint);
                }
                //支付产生的积分，需要扣减
                int point2 = order.getPaymentFee();
                int currPoint = localPoint.getPoint() - point2;
                //积分扣减日志
                savePointLog(order, "0", -point2, currPoint);
                //积分返还
                pointMapper.updatePoint(order.getUserId(), -point2);
            } else {
                return BaseResult.error(wxRefundNotifyResponseData.getReturn_code(),
                        wxRefundNotifyResponseData.getReturn_msg());
            }
        }
        return BaseResult.success("退款成功");
    }

    private void saveGoodLog(String goodName, Long goodId, String action) {
        GoodLog goodLog = new GoodLog();
        goodLog.setAction(action);
        goodLog.setGoodId(goodId);
        goodLog.setGoodName(goodName);
        goodLogMapper.add(goodLog);
    }

    private void savePointLog(Order order, String action, Integer operatePoint, Integer currentPoint) {
        PointLog pointLog = new PointLog();
        pointLog.setAction(action);
        pointLog.setUserId(order.getUserId());
        pointLog.setOperatePoint(operatePoint);
        pointLog.setCurrentPoint(currentPoint);
        wxPointLogMapper.add(pointLog);
    }

    private void saveOrderLog(Long orderId, String orderNo, String action) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderId);
        orderLog.setIpAddress("");
        orderLog.setOrderNo(orderNo);
        orderLog.setAction(action);
        orderLogMapper.add(orderLog);
    }

    private String returnMessage(String code) {
        String result = "";
        switch (code) {
            case "SYSTEMERROR":
                result = "接口返回错误";
                break;
            case "USER_ACCOUNT_ABNORMAL":
                result = "请求退款失败";
                break;
            case "NOTENOUGH":
                result = "余额不足";
                break;
            case "INVALID_TRANSACTIONID":
                result = "无效的订单号";
                break;
            case "PARAM_ERROR":
                result = "参数错误";
                break;
            case "APPID_NOT_EXIST":
                result = "APPID不存在";
                break;
            case "MCHID_NOT_EXIST":
                result = "MCHID不存在";
                break;
            case "APPID_MCHID_NOT_MATCH":
                result = "appid和mch_id不匹配";
                break;
            case "REQUIRE_POST_METHOD":
                result = "请使用post方法";
                break;
            case "SIGNERROR":
                result = "签名错误";
                break;
            case "XML_FORMAT_ERROR":
                result = "XML格式错误";
                break;
        }
        return result;
    }

    /**
     * 退款读取证书
     *
     * @param orderNo
     * @param total_fee
     * @param refund_fee
     * @return
     */
    private String refundSign(
            String orderNo,
            int total_fee,
            int refund_fee,
            String refundNo,
            String nonce_str) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonce_str);
        map.put("out_trade_no", orderNo);
        map.put("out_refund_no", refundNo);
        map.put("total_fee", total_fee);
        map.put("refund_fee", refund_fee);
        map.put("op_user_id", "100000");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.mapConvertToXML(map);
    }

}
