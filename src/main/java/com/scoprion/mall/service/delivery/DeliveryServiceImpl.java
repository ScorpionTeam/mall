package com.scoprion.mall.service.delivery;

import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.mapper.DeliveryMapper;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;


    /**
     * 创建收货地址 并且返回收货地址id
     *
     * @param delivery
     * @return
     */
    @Override
    public BaseResult add(Delivery delivery) {
        int result = deliveryMapper.add(delivery);
        if (result <= 0) {
            return BaseResult.error("add_fail", "创建收获地址失败.");
        }
        return BaseResult.success(delivery);
    }

    @Override
    public List<Delivery> findAllByUserId(Long userId) {
        return null;
    }


}
