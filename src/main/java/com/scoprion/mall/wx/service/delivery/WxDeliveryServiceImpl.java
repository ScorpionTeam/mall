package com.scoprion.mall.wx.service.delivery;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.wx.mapper.WxDeliveryMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hmy
 * @date 2017/11/1
 */
@Service
public class WxDeliveryServiceImpl implements WxDeliveryService {

    @Autowired
    private WxDeliveryMapper wxDeliveryMapper;

    /**
     * 分页查询用户收货地址列表
     *
     * @param wxCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findByWxCode(String wxCode, Integer pageNo, Integer pageSize) {
        String openId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        //判断userId是否为空
        if (StringUtils.isEmpty(openId)) {
            return new PageResult();
        }
        Page<Delivery> page = wxDeliveryMapper.listPage(openId);
        return new PageResult(page);
    }

    /**
     * 新增收货地址
     *
     * @param delivery
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult add(Delivery delivery) {
        delivery.setDefaultAddress(CommonEnum.DEFAULT_ADDRESS.getCode());
        Integer result = wxDeliveryMapper.add(delivery);
        if (result <= 0) {
            return BaseResult.error("error", "新增失败");
        }
        Integer defaultResult=wxDeliveryMapper.updateDefaultById(delivery.getId(),delivery.getUserId());
        if (defaultResult <= 0){
            return BaseResult.error("error","修改默认地址失败");
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
        wxDeliveryMapper.updateDelivery(delivery);
        return BaseResult.success("修改成功");
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteDelivery(Long id) {
        Integer result = wxDeliveryMapper.deleteDelivery(id);
        if (result <= 0) {
            return BaseResult.error("error", "删除失败");
        }
        return BaseResult.success("删除成功");
    }

    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        Delivery delivery = wxDeliveryMapper.findById(id);
        if (delivery == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(delivery);
    }

    /**
     * 设置默认收货地址
     * @param id
     * @return
     */
    @Override
    public BaseResult defaultAddress(Long id,String wxCode) {
        String userId = WxUtil.getOpenId(wxCode);
        int result = wxDeliveryMapper.updateDefaultAddress(id);
        if (result<=0){
            return BaseResult.error("update_fail","设置默认地址失败");
        }
        Integer defaultResult=wxDeliveryMapper.updateDefaultById(id,userId);
        if (defaultResult <= 0){
            return BaseResult.error("error","修改默认地址失败");
        }
        return BaseResult.success("设置成功");
    }

    /**
     * 获取默认地址
     * @param wxCode
     * @return
     */
    @Override
    public BaseResult getDefault(String wxCode) {
        String userId=WxUtil.getOpenId(wxCode);
        if(StringUtils.isEmpty(wxCode)){
            return BaseResult.parameterError();
        }
        Delivery delivery=wxDeliveryMapper.getDefault(userId);
        if(delivery == null){
            return BaseResult.notFound();
        }
        return BaseResult.success(delivery);
    }

}
