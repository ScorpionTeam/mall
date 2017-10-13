package com.scoprion.mall.mapper;

import com.scoprion.mall.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2017/10/13.
 */
@Mapper
public interface DeliveryMapper {

    int add(Delivery delivery);
    
}
