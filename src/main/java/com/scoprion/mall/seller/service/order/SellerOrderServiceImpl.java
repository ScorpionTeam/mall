package com.scoprion.mall.seller.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.backstage.mapper.OrderLogMapper;
import com.scoprion.mall.backstage.mapper.PointMapper;
import com.scoprion.mall.backstage.mapper.SendGoodMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.mall.domain.SendGood;
import com.scoprion.mall.domain.order.Order;
import com.scoprion.mall.domain.order.OrderExt;
import com.scoprion.mall.domain.request.OrderRequestParams;
import com.scoprion.mall.seller.mapper.SellerGoodMapper;
import com.scoprion.mall.seller.mapper.SellerOrderMapper;
import com.scoprion.mall.seller.service.order.SellerOrderService;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.DateParamFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:27
 */
@Service
public class SellerOrderServiceImpl implements SellerOrderService {

    @Autowired
    SellerOrderMapper sellerOrderMapper;


    @Autowired
    private SellerGoodMapper sellerGoodMapper;


    @Autowired
    private OrderLogMapper orderLogMapper;


    @Autowired
    private GoodLogMapper goodLogMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private WxPointLogMapper wxPointLogMapper;

    @Autowired
    private SendGoodMapper sendGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    /**
     * 订单列表
     *
     * @param requestParams
     * @return
     */
    @Override
    public PageResult findByCondition(OrderRequestParams requestParams) {
        PageHelper.startPage(requestParams.getPageNo(), requestParams.getPageSize());

        requestParams.setStartDate(DateParamFormatUtil.formatDate(requestParams.getStartDate()));
        requestParams.setEndDate(DateParamFormatUtil.formatDate(requestParams.getEndDate()));

        if (StringUtils.isEmpty(requestParams.getSearchKey())) {
            requestParams.setSearchKey(null);
        }
        if (!StringUtils.isEmpty(requestParams.getSearchKey())) {
            requestParams.setSearchKey("%" + requestParams.getSearchKey() + "%");
        }
        Page<OrderExt> page = sellerOrderMapper.findByCondition(requestParams);
        return new PageResult(page);
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
        OrderExt order = sellerOrderMapper.findById(id);
        if (order == null) {
            return BaseResult.notFound();
        }
        Delivery delivery = wxDeliveryMapper.findById(order.getDeliveryId());
        order.setDelivery(delivery);

        SendGood sendGood = sendGoodMapper.findById(order.getSendGoodId());
        order.setSendGood(sendGood);
        return BaseResult.success(order);
    }

    /**
     * 修改
     *
     * @param order
     * @return
     */
    @Override
    public BaseResult modify(Order order) {
        if (order.getId() == null) {
            return BaseResult.parameterError();
        }
        Order localOrder = sellerOrderMapper.findById(order.getId());
        if (CommonEnum.UN_PAY.getCode().equals(localOrder.getOrderStatus())) {
            return BaseResult.error("modify_error", "未付款的订单不能修改");
        }
        sellerOrderMapper.modify(order);
        saveOrderLog(order.getId(), order.getOrderNo(), "修改订单");
        return BaseResult.success("修改成功");
    }

    /**
     * 退款
     *
     * @param orderId   订单id
     * @param flag      AGREE 同意  REFUSE  拒绝
     * @param remark    退款备注
     * @param refundFee 退款金额
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult refund(Long orderId, String flag, String remark, int refundFee) {
        if (orderId == null) {
            return BaseResult.parameterError();
        }
        Order order = sellerOrderMapper.findById(orderId);
        if (CommonEnum.REFUSE.getCode().equals(flag)) {
            sellerOrderMapper.updateOrderRefundById(orderId, CommonEnum.REFUND_FAIL.getCode(), 0, remark);
            saveOrderLog(orderId, order.getOrderNo(), remark);
            return BaseResult.success("审核完成");
        } else {
//            String nonceStr = WxUtil.createRandom(false, 10);
//            Order order = sellerOrderMapper.findById(orderId);
//            String refundOrderNo = order.getOrderNo() + "T";
//            //定义接收退款返回字符串
//            String refundXML = refundSign(order.getOrderNo(), order.getPaymentFee(), refundFee, refundOrderNo,
//                    nonceStr);
//            //接收退款返回
//            String response = null;
//            try {
//                response = WxPayUtil.doRefund(WxPayConfig.WECHAT_REFUND, refundXML, WxPayConfig.MCHID);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return BaseResult.systemError();
//            }
//            WxRefundNotifyResponseData wxRefundNotifyResponseData = WxPayUtil.castXMLStringToWxRefundNotifyResponseData(
//                    response);
//            Boolean result = "success".equalsIgnoreCase(wxRefundNotifyResponseData.getReturn_code());
//            if (result) {
            sellerOrderMapper.updateOrderRefundById(order.getId(), CommonEnum.REFUNDING.getCode(), refundFee, remark);
            saveOrderLog(order.getId(), order.getOrderNo(), "退款");
            sellerGoodMapper.updateGoodStock(order.getGoodId(), order.getCount());
            //记录商品库存反还日志
            saveGoodLog(order.getGoodName(), order.getGoodId(), "退款，商品库存返还");
            //使用积分返还
            //支付时扣减的积分，需要返还
            Point localPoint = pointMapper.findByUserId(order.getUserId());
            int operatePoint = order.getOperatePoint();
            if (CommonEnum.USE_POINT.getCode().equals(order.getUsePoint())) {
                //积分返还
                pointMapper.updatePoint(order.getUserId(), operatePoint);
                //记录积分返还日志
                savePointLog(order, CommonEnum.PRODUCE_POINT.getCode(), +operatePoint, localPoint.getPoint() + operatePoint);
                localPoint.setPoint(localPoint.getPoint() + operatePoint);
            }
            //支付产生的积分，需要扣减
            int point2 = order.getPaymentFee();
            int currPoint = localPoint.getPoint() - point2;
            //积分扣减日志
            savePointLog(order, CommonEnum.CONSUME_POINT.getCode(), -point2, currPoint);
            //积分返还
            pointMapper.updatePoint(order.getUserId(), -point2);
//        } else{
//            return BaseResult.error(wxRefundNotifyResponseData.getReturn_code(),
//                    wxRefundNotifyResponseData.getReturn_msg());
//        }
        }
        return BaseResult.success("退款成功");
    }

    /**
     * 退货
     *
     * @return
     */
    @Override
    public BaseResult goodReject(Long orderId, Integer count) {
        Order order = sellerOrderMapper.findById(orderId);
        //返回库存--商品规则返库
        sellerGoodMapper.updateGoodStock(order.getGoodId(), count);
        //关闭该订单
        order.setOrderStatus(CommonEnum.CLOSING.getCode());
        sellerOrderMapper.modify(order);
        saveOrderLog(orderId, order.getOrderNo(), "退货");
        saveGoodLog(order.getGoodName(), order.getGoodId(), "订单退货,返回库存");
        return BaseResult.success("退货成功");
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
            return BaseResult.error("send_error", "运单号不能为空");
        }
        if (StringUtils.isEmpty(expressName)) {
            return BaseResult.error("send_error", "快递公司不能为空");
        }
        if (senderId == null) {
            return BaseResult.error("send_error", "发件人id不能为空");
        }
        Order order = sellerOrderMapper.findById(orderId);
        //生成发货信息记录，
        SendGood sendGood = saveSendGoodInfo(orderId, deliveryNo, expressName, expressNo, senderId);
        //修改订单信息，发货时间、状态3(待收货)，发货信息id，
        sellerOrderMapper.updateSendGood(orderId, CommonEnum.UN_RECEIVE.getCode(), deliveryNo, sendGood.getId());
        //生成订单日志
        saveOrderLog(orderId, order.getOrderNo(), "订单发货");
        return BaseResult.success("操作成功");
    }

    @Override
    public PageResult findOrderLogByOrderId(Integer pageNo, Integer pageSize, Long orderId) {
        return null;
    }

    private SendGood saveSendGoodInfo(Long orderId, String deliveryNo, String expressName, String expressNo, Long senderId) {
        SendGood sendGood = new SendGood();
        sendGood.setExpressName(expressName);
        sendGood.setDeliveryNo(deliveryNo);
        sendGood.setExpressNo(expressNo);
        sendGood.setSenderId(senderId);
        sendGood.setOrderId(orderId);
        sendGoodMapper.add(sendGood);
        return sendGood;
    }

    private void saveGoodLog(String goodName, Long goodId, String action) {
        ServiceCommon.saveGoodLog(goodName, action, goodId, goodLogMapper);
    }

    private void savePointLog(Order order, String action, Integer operatePoint, Integer currentPoint) {
        PointLog pointLog = new PointLog();
        pointLog.setAction(action);
        pointLog.setUserId(order.getUserId());
        pointLog.setCurrentPoint(currentPoint);
        pointLog.setOperatePoint(operatePoint);
        wxPointLogMapper.add(pointLog);
    }

    /**
     * 保存订单日志
     *
     * @param orderId
     * @param orderNo
     * @param action
     */
    private void saveOrderLog(Long orderId, String orderNo, String action) {
        ServiceCommon.saveOrderLog(orderId, null, orderNo, action, orderLogMapper);
    }

    /**
     * 退款读取证书
     *
     * @param orderNo
     * @param totalFee
     * @param refundFee
     * @return
     */
    private String refundSign(
            String orderNo,
            int totalFee,
            int refundFee,
            String refundNo,
            String nonceStr) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("out_trade_no", orderNo);
        map.put("out_refund_no", refundNo);
        map.put("total_fee", totalFee);
        map.put("op_user_id", "100000");
        map.put("refund_fee", refundFee);
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.mapConvertToXML(map);
    }
}
