package com.scoprion.mall.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.MallLog;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.mapper.GoodMapper;
import com.scoprion.mall.mapper.GoodSnapShotMapper;
import com.scoprion.mall.mapper.MallLogMapper;
import com.scoprion.mall.mapper.OrderMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created on 2017/9/29.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private MallLogMapper mallLogMapper;

    @Autowired
    private GoodSnapShotMapper goodSnapShotMapper;

    /**
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @param status   订单状态
     * @param userId   用户id
     * @return
     */
    @Override
    public PageResult findByPage(int pageNo, int pageSize, String status, Long userId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Order> result = orderMapper.findByPage(status, userId);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @param ipAddress
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized BaseResult orderConfirm(Long goodId, Long deliveryId, String ipAddress) throws Exception {
        Good good = goodMapper.findById(goodId);
        if (null == good) {
            return BaseResult.error("good_not_found", "商品不存在.");
        }
        if (good.getStock() <= 0) {
            return BaseResult.error("confirm_fail", "库存不足.");
        }
        //组装商品快照信息
        GoodSnapshot goodSnapshot = this.constructGoodSnapShot(good);
        goodSnapShotMapper.add(goodSnapshot);
        //组装快照日志
        MallLog snapShotLog = this.constructLog(ipAddress,goodSnapshot.getGoodSnapShotNo(),null,"5","生成商品快照");
        mallLogMapper.add(snapShotLog);
        //组装订单信息
        Order order = this.constructOrder(goodSnapshot, deliveryId);
        orderMapper.add(order);
        //组装日志信息
        MallLog orderLog = this.constructLog(ipAddress, good.getGoodNo(), null, "2", "订单生成");
        mallLogMapper.add(orderLog);
        //扣减商品库存
        goodMapper.goodDeduction(goodId);
        //记录商品扣减日志
        MallLog goodLog = this.constructLog(ipAddress, good.getGoodNo(), null, "4", "商品库存扣减");
        mallLogMapper.add(goodLog);
        return BaseResult.success("下单成功.");
    }

    /**
     * 组装订单信息
     *
     * @param goodSnapshot
     * @param deliveryId
     * @return
     */
    private Order constructOrder(GoodSnapshot goodSnapshot, Long deliveryId) {
        Order order = new Order();
        //生成订单号码
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setDeliveryId(deliveryId);
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setOrderStatus("1");
        order.setOrderType("2");
        order.setPayDate(new Date());
        order.setPayType("1");
        return order;
    }

    /**
     * 组装商品快照
     *
     * @param good
     * @return
     */
    private GoodSnapshot constructGoodSnapShot(Good good) throws Exception {
        //生成商品快照编码
        String goodSnapShotNo = String.valueOf(IDWorker.getFlowIdWorkerInstance().nextId());
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        goodSnapshot.setGoodSnapShotNo(goodSnapShotNo);
        BeanUtils.copyProperties(good, goodSnapshot);
        return goodSnapshot;
    }

    /**
     * 日志组装
     *
     * @param ipAddress
     * @param goodNo
     * @param sellerNo
     * @param type
     * @param description
     * @return
     */
    private MallLog constructLog(String ipAddress, String goodNo, String sellerNo, String type, String description) {
        MallLog mallLog = new MallLog();
        mallLog.setGoodNo(goodNo);
        mallLog.setIpAddress(ipAddress);
        mallLog.setSellerNo(sellerNo);
        mallLog.setType(type);
        mallLog.setDescription(description);
        return mallLog;
    }


}
