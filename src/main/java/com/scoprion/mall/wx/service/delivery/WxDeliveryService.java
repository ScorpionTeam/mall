package com.scoprion.mall.wx.service.delivery;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created by admin1 on 2017/11/1.
 */
public interface WxDeliveryService {

    /**
     * 查询用户收获地址列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult listPage(Long userId, Integer pageNo, Integer pageSize);

    /**
     * 新增收货地址
     * @param delivery
     * @return
     */
    BaseResult add(Delivery delivery);

    /**
     * 修改收货地址
     *
     * @param delivery
     * @return
     */
    BaseResult updateDelivery(Delivery delivery);

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    BaseResult deleteDelivery(Long id);

    /**
     * 获取收货地址详情
     * @param id
     * @return
     */
    BaseResult findById(Long id);
}
