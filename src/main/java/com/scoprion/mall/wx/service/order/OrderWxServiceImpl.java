package com.scoprion.mall.littlesoft.service.order;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.GoodSnapShotMapper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.MallLog;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.wx.mapper.GoodWxMapper;
import com.scoprion.mall.wx.mapper.MallLogWxMapper;
import com.scoprion.mall.wx.mapper.OrderLogWxMapper;
import com.scoprion.mall.wx.mapper.OrderWxMapper;
import com.scoprion.mall.wx.service.order.OrderWxService;
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
 * @author by admin1
 * @created on 2017/11/2.
 */
@Service
public class OrderWxServiceImpl implements OrderWxService {
    @Autowired
    private OrderWxMapper orderWxMapper;
    @Autowired
    private GoodWxMapper goodWxMapper;
    @Autowired
    private GoodSnapShotMapper goodSnapShotMapper;
    @Autowired
    private MallLogWxMapper mallLogWxMapper;
    @Autowired
    private OrderLogWxMapper orderLogWxMapper;

    /**
     * 分页获取我的订单列表
     *
     * @param userId
     * @param status   0 全部
     *                 1 待付款
     *                 2 待发货
     *                 3 待收货
     *                 4 已完成
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getMyOrder(Long userId, String status, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        //若userId,status传空或没传,直接返回
        if (StringUtils.isEmpty(userId.toString()) || StringUtils.isEmpty(status)) {
            return new PageResult();
        }
        Page<Order> page = orderWxMapper.getOrderList(userId, status);
        return new PageResult(page);
    }

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @param ipAdress
     * @param purchase:订单量
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult ordered(Long goodId, Long deliveryId, String ipAdress, Integer purchase) throws Exception {
        Good good = goodWxMapper.goodDetail(goodId);
        if (good == null) {
            return BaseResult.error("error", "商品不存在");
        } else if (good.getStock() < purchase) {
            return BaseResult.error("error", "商品库存量不足");
        }

        //组装生成商品快照
        GoodSnapshot goodSnapshot = this.constructWxGoodSnapShot(good);
        goodSnapShotMapper.add(goodSnapshot);
        //组装商品快照日志
        MallLog goodSnapShotLog = this.constructLog(ipAdress, goodSnapshot.getGoodSnapShotNo(), null, "5", "生成商品快照");
        mallLogWxMapper.add(goodSnapShotLog);
        //组装订单
        Order order = this.constructOrder(goodSnapshot, deliveryId);
        orderWxMapper.add(order);
        //组装订单快照日志
//        MallLog orderLog = this.constructLog(ipAdress, goodSnapshot.getGoodSnapShotNo(), null, "2", "生成订单");
//        orderLogWxMapper.add(orderLog);
        //扣减库存
        goodWxMapper.goodDeduct(goodId, purchase);
        //扣减日志
        MallLog goodLog = this.constructLog(ipAdress, goodSnapshot.getGoodSnapShotNo(), null, "4", "商品库存扣减");
        mallLogWxMapper.add(goodLog);
        return BaseResult.success("下单成功");
    }

    /**
     * 生成商品快照
     *
     * @param good
     * @return
     */
    private GoodSnapshot constructWxGoodSnapShot(Good good) throws Exception {
        String snapshotNo = String.valueOf(IDWorker.getFlowIdWorkerInstance().nextId());
        GoodSnapshot snapShot = new GoodSnapshot();
        snapShot.setGoodSnapShotNo(snapshotNo);
        BeanUtils.copyProperties(good, snapShot);
        return snapShot;
    }


    private Order constructOrder(GoodSnapshot goodSnapshot, Long deliveryId) {
        Order order = new Order();
        //生成订单编号
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setDeliveryId(deliveryId);
//        order.setGoodSnapshot(goodSnapshot);
        order.setGoodSnapShotId(goodSnapshot.getId());
        order.setPayType("1");
        order.setOrderStatus("1");
        order.setOrderType("2");
        order.setCreateDate(new Date());
        return order;
    }

    /**
     * 组装日志
     *
     * @param ipAdress
     * @param goodNo
     * @param sellNo
     * @param type
     * @param description
     * @return
     */
    private MallLog constructLog(String ipAdress, String goodNo, String sellNo, String type, String description) {
        MallLog mallLog = new MallLog();
        mallLog.setIpAddress(ipAdress);
        mallLog.setGoodNo(goodNo);
        mallLog.setSellerNo(sellNo);
        mallLog.setType(type);
        mallLog.setDescription(description);
        return mallLog;
    }


}
