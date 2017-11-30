package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author hmy
 * @date 2017/11/1
 */
@Mapper
public interface WxDeliveryMapper {

    /**
     * 分页查询用户收货地址
     *
     * @param userId
     * @return
     */
    Page<Delivery> listPage(@Param("userId") String userId);

    /**
     * 新增收货地址
     *
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
     *
     * @param id
     * @return
     */
    Integer deleteDelivery(@Param("id") Long id);

    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    Delivery findById(@Param("id") Long id);

    /**
     * 设置为默认地址
     *
     * @param id
     * @return
     */
    int updateDefaultAddress(@Param("id") Long id);

    /**
     * 更改该用户其他地址不为默认地址
     *
     * @param id
     * @param userId
     * @return
     */
    int updateDefaultById(@Param("id") Long id, @Param("userId") String userId);

    /**
     * 获取默认地址
     *
     * @param userId
     * @return
     */
    Delivery getDefault(@Param("userId") String userId);
}
