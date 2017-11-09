package com.scoprion.mall.backstage.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.OrderLogMapper;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.mall.domain.OrderLog;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PageResult listPage(Integer pageNo, Integer pageSize, String payType, String orderType,
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
        Page<Order> orderPage = orderMapper.listPage(payType, orderType, orderStatus, searchKey, startDate, endDate,
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
        Order order = orderMapper.findById(id);
        if (order == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(order);
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
        }
        String nonce_str = WxUtil.createRandom(false, 10);
        Order order = orderMapper.findById(orderId);
        String refundOrderNo = order.getOrderNo() + "T";
        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonce_str);
        map.put("transaction_id", order.getWxOrderNo());
        map.put("out_refund_no", refundOrderNo);
        map.put("total_fee", order.getTotalFee());
        map.put("refund_fee", refundFee);
        map.put("op_user_id", "100000");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        String refundXML = WxPayUtil.castDataToXMLString(map);
        System.out.println("退款参数:" + refundXML);
        //定义接收退款返回字符串
        String response = WxUtil.httpsRequest(WxPayConfig.WECHAT_REFUND, "POST", refundXML);
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
            //TODO 积分返还

        }
        //TODO  put  sign
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
}
