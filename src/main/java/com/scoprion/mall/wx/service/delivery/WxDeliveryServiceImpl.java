package com.scoprion.mall.wx.service.delivery;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin1
 * @date 2017/11/1
 */
@Service
public class WxDeliveryServiceImpl implements WxDeliveryService {

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

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
        Page<Delivery> page = wxDeliveryMapper.deliveryList(userId);
        return new PageResult(page);
    }

    /**
     * 新增收货地址
     *
     * @param delivery
     * @return
     */
    @Override
    public BaseResult addDelivery(Delivery delivery) {
        Integer result = wxDeliveryMapper.addDelivery(delivery);
        if(result <=0){
            return  BaseResult.error("error","新增失败");
        }
        return BaseResult.success("新增成功");
    }

    /**
     * 修改收货地址
     *
     * @param delivery
     * @return
     */
    @Override
    public BaseResult updateDelivery(Delivery delivery) {
        Integer result = wxDeliveryMapper.updateDelivery(delivery);
        if(result <=0){
            return  BaseResult.error("error","修改失败");
        }
        return BaseResult.success("修改成功");
    }

    /**
     * 删除收获地址
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteDelivery(Long id) {
        Integer result = wxDeliveryMapper.deleteDelivery(id);
        if(result<=0){
            return BaseResult.error("error","删除失败");
        }
        return BaseResult.success("删除成功");
    }
}
