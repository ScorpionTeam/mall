package com.scoprion.mall.backstage.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.*;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
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
    private PointMapper pointMapper;

    @Autowired
    private SendGoodMapper sendGoodMapper;

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    /**
     * 换货
     *
     * @return
     */
    public BaseResult exchangeGood(Long orderId, Long goodId) {
        Order order = orderMapper.findById(orderId);
        if (goodId.longValue() == order.getGoodId().longValue()) {
            //相同规格的商品，直接换货
        } else {
            //不同规格的商品，价格不同

        }
        return BaseResult.success("操作成功");
    }

    /**
     * 退货
     *
     * @return
     */
    public BaseResult goodReject(Long orderId, Integer count) {
        Order order = orderMapper.findById(orderId);
        //返回库存
        goodsMapper.modifyGoodsDeduction(order.getGoodId(), count);
        //待评价
        order.setOrderStatus("6");
        orderMapper.modify(order);
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
        if (senderId == null) {
            return BaseResult.error("sender_error", "发件人id不能为空");
        }
        Order order = orderMapper.findById(orderId);
        //生成发货信息记录，
        SendGood sendGood = new SendGood();
        sendGood.setDeliveryNo(deliveryNo);
        sendGood.setExpressName(expressName);
        sendGood.setExpressNo(expressNo);
        sendGood.setOrderId(orderId);
        sendGood.setSenderId(senderId);
        sendGoodMapper.add(sendGood);
        //修改订单信息，发货时间、状态3(待收货)，发货信息id，
        orderMapper.updateSendGood(orderId, 3, deliveryNo, sendGood.getId());
        //生成订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNo(order.getOrderNo());
        orderLog.setOrderId(orderId);
        orderLog.setAction("发货");
        orderLogMapper.add(orderLog);
        //商品库存扣减
        //goodsMapper.modifyGoodsDeduction(order.getGoodId(), -order.getCount());
        return BaseResult.success("操作成功");
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
        Page<Order> orderPage = orderMapper.findByCondition(payType, orderType, orderStatus, searchKey, startDate,
                endDate,
                phone, orderNo);
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
        //1 待付款 2 待发货3 待收货 4 已完成 5 退款 6 关闭 7 待评价 8 已评价
        Order localOrder = orderMapper.findById(order.getId());
        if (Constant.STATUS_ONE.equals(localOrder.getOrderStatus())) {
            return BaseResult.error("modify_error", "未付款的订单不能修改");
        }
        orderMapper.modify(order);
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
    public BaseResult refund(Long orderId, String flag, String remark, int refundFee) {
        if ("0".equals(flag)) {
            orderMapper.updateOrderRefundById(orderId, "7", remark);
            return BaseResult.success("审核完成");
        } else {
            String nonce_str = WxUtil.createRandom(false, 10);
            Order order = orderMapper.findById(orderId);
            String refundOrderNo = order.getOrderNo() + "T";
            //定义接收退款返回字符串
            String response = wxRefundCert("hk111111",
                    order.getOrderNo(),
                    order.getPaymentFee(),
                    refundFee,
                    WxPayConfig.CERT_SECRET,
                    refundOrderNo,
                    nonce_str);
            System.out.println("返回数据:" + response);
            WxRefundNotifyResponseData wxRefundNotifyResponseData = WxPayUtil.castXMLStringToWxRefundNotifyResponseData(
                    response);
            Boolean result = "success".equalsIgnoreCase(wxRefundNotifyResponseData.getReturn_code());
            if (result) {
                orderMapper.updateOrderRefundById(order.getId(), "6", "");
                OrderLog orderLog = new OrderLog();
                orderLog.setOrderId(order.getId());
                orderLog.setIpAddress("");
                orderLog.setOrderNo(order.getOrderNo());
                orderLog.setAction("退款");
                orderLogMapper.add(orderLog);
                //TODO 商品库存返还
                goodsMapper.updateGoodStockById(order.getGoodId(), order.getCount());
                //记录商品库存反还日志

                //TODO 积分返还  10块钱  = 1积分
                int point = order.getPaymentFee() / 1000;
                pointMapper.updatePoint(order.getUserId(), point);
                //记录积分反还日志


            } else {
                return BaseResult.error(wxRefundNotifyResponseData.getReturn_code(),
                        wxRefundNotifyResponseData.getReturn_msg());
            }
        }

        return null;
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
     * @param shh
     * @param orderNo
     * @param total_fee
     * @param refund_fee
     * @param path
     * @return
     */
    private String wxRefundCert(
            String shh,
            String orderNo,
            int total_fee,
            int refund_fee,
            String path,
            String refundNo,
            String nonce_str) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonce_str);
        map.put("transaction_id", orderNo);
        map.put("out_refund_no", refundNo);
        map.put("total_fee", total_fee);
        map.put("refund_fee", refund_fee);
        map.put("op_user_id", "100000");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        String xml = WxPayUtil.mapConvertToXML(map);
        String responseXML = "";
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream inputStream = new FileInputStream(new File(path));
            try {
                keyStore.load(inputStream, shh.toCharArray());
            } finally {
                inputStream.close();
            }
            responseXML = WxUtil.httpsRequest(WxPayConfig.WECHAT_REFUND, "POST", xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseXML;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000);
    }
}
