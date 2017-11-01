package com.scoprion.mall.littlesoft.service.delivery;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.littlesoft.mapper.DeliveryLittleSoftMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin1 on 2017/11/1.
 */
@Service
public class DeliveryListLittleSoftServiceImpl implements DeliveryLittleSoftService {

    @Autowired
    private DeliveryLittleSoftMapper deliveryLittleSoftMapper;

    /**
     * 分页查询用户收获地址列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult DeliveryList(Long userId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        //判断userId是否为空
        if(userId==null){
            return new PageResult();
        }
        Page<Delivery> page = deliveryLittleSoftMapper.deliveryList(userId);
        return new PageResult(page);
    }
}
