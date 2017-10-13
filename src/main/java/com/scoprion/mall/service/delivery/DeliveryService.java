package com.scoprion.mall.service.delivery;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.result.BaseResult;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
public interface DeliveryService {

    /**
     * 创建收货地址
     * @param delivery
     * @return
     */
    BaseResult add(Delivery delivery);

    /**
     * 根据用户id查询收货地址
     * @param userId
     * @return
     */
    List<Delivery> findAllByUserId(Long userId);
}
