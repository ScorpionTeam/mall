package com.scoprion.mall.wx.service.delivery;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * Created by admin1 on 2017/11/1.
 */
public interface WxDeliveryService {


    /**
     * 查询收货地址列表
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageResult findByUserId(int pageNo, int pageSize, String userId);

    /**
     * 新增收货地址
     *
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
     * 删除收获地址
     *
     * @param id
     * @return
     */
    BaseResult deleteDelivery(Long id);
}
