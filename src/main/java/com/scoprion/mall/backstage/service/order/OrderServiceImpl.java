package com.scoprion.mall.backstage.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.MallLog;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.mall.backstage.mapper.GoodSnapShotMapper;
import com.scoprion.mall.backstage.mapper.MallLogMapper;
import com.scoprion.mall.backstage.mapper.OrderMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import com.scoprion.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
     * 生成商品快照
     * 生成订单信息
     * 生成快照 订单日志
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
        MallLog snapShotLog = this.constructLog(ipAddress, goodSnapshot.getGoodSnapShotNo(), null, "5", "生成商品快照");
        mallLogMapper.add(snapShotLog);
        //组装订单信息
        Order order = this.constructOrder(goodSnapshot, deliveryId);
        orderMapper.add(order);
        //组装日志信息
        MallLog orderLog = this.constructLog(ipAddress, good.getGoodNo(), null, "2", "订单生成");
        mallLogMapper.add(orderLog);
        //扣减商品库存
        goodMapper.modifyGoodDeduction(goodId, -1);
        //记录商品扣减日志
        MallLog goodLog = this.constructLog(ipAddress, good.getGoodNo(), null, "4", "商品库存扣减");
        mallLogMapper.add(goodLog);
        return BaseResult.success("下单成功.");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult mockList() {
        List<Order> orders = orderMapper.mockList();
        return BaseResult.success(orders);
    }

    /**
     * 我的订单
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @param userId   用户id
     * @param status   状态
     * @return
     */
    @Override
    public PageResult myOrder(int pageNo, int pageSize, Long userId, String status) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Order> page = orderMapper.myOrder(userId, status);
        List<Order> orderList = page.getResult();
        if (null != orderList && orderList.size() > 0) {
            for (Order order : orderList) {
//                GoodSnapshot goodSnapshot = goodSnapShotMapper.findById(order.getGoodSnapShotId());
//                order.setGoodSnapshot(goodSnapshot);
            }
        }
        return new PageResult(page);
    }


    /**
     * 模拟服务  非正式服务
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Order> mockList1() {
        List<Order> orders = orderMapper.mockList();
        return orders;
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
