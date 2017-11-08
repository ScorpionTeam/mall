package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Created by admin1 on 2017/11/1.
 */
@Mapper
public interface WxDeliveryMapper {

    /**
     * 分页查询用户收获地址
     *
     * @param userId
     * @return
     */
    Page<Delivery> deliveryList(@Param("userId") Long userId);

    /**
     * 新增收货地址
     * @param delivery
     * @return
     */
    int add(Delivery delivery);

    /**
     * 修改收货地址
     *
     * @param delivery
     * @return
     */
    Integer updateDelivery(Delivery delivery);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    Integer deleteByDelivery(@Param("id") Long id);

    /**
     * 根据主键查询收件人信息
     * @param id
     * @return
     */
    Delivery findById(@Param("id") Long id);
}
