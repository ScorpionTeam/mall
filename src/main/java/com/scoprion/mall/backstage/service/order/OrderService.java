package com.scoprion.mall.backstage.service.order;

import com.scoprion.mall.domain.Order;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created on 2017/9/29.
 *
 * @author ycj
 */
public interface OrderService {

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
    PageResult findByCondition(Integer pageNo, Integer pageSize, String payType, String orderType,
                               String orderStatus, String searchKey, String startDate, String endDate,
                               String phone, String orderNo);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    BaseResult modify(Order order);

    /**
     * 退款
     *
     * @param orderId
     * @param flag
     * @param remark
     * @param refundFee
     * @return
     */
    BaseResult refund(Long orderId, String flag, String remark, int refundFee);

    /**
     * 换货
     *
     * @param orderId      订单id
     * @param goodId       订单中需要换货的id
     * @param targetGoodId 需要换成哪个规格的商品的id
     * @return
     */
    BaseResult exchangeGood(Long orderId, Long goodId, Long targetGoodId);

    /**
     * 退货
     *
     * @param orderId 订单id
     * @param count   退货数量
     * @return
     */
    BaseResult goodReject(Long orderId, Integer count);

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
    BaseResult sendGood(Long orderId, String deliveryNo, String expressName,
                        String expressNo, Long senderId);
}
