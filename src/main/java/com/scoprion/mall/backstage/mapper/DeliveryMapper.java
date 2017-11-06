package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
@Mapper
public interface DeliveryMapper {

    /**
     * 创建收货地址
     *
     * @param delivery
     * @return
     */
    int add(Delivery delivery);


    /**
     * 根据用户id查询收货地址列表
     *
     * @param userId
     * @return
     */
    List<Delivery> findAllByUserId(Long userId);

    /**
     * 编辑收货地址
     *
     * @param delivery
     * @return
     */
    int edit(Delivery delivery);

    /**
     * 根据id查询收货地址
     *
     * @param id
     * @return
     */
    Delivery findById(Long id);

}
