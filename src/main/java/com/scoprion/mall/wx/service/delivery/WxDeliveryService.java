package com.scoprion.mall.wx.service.delivery;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.DeliveryExt;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author hmy
 * @date 2017/11/1
 */
public interface WxDeliveryService {

    /**
     * 查询用户收货地址列表
     *
     * @param wxCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findByWxCode(String wxCode, Integer pageNo, Integer pageSize);

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
     * 删除收货地址
     * @param deliveryExt
     * @return
     */
    BaseResult deleteDelivery(DeliveryExt deliveryExt);

    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 默认收货地址
     *
     * @param id
     * @param wxCode
     * @return
     */
    BaseResult defaultAddress(Long id, String wxCode);

    /**
     * 获取默认地址
     *
     * @param wxCode
     * @return
     */
    BaseResult getDefault(String wxCode);
}
