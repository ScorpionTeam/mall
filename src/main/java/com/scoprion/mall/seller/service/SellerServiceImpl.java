package com.scoprion.mall.seller.service;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.seller.mapper.SellerMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by hmy
 * @created on 2017/12/7/007.
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 商户店铺建立
     * @param seller
     * @return
     * @throws Exception
     */
    @Override
    public BaseResult register(Seller seller) throws Exception {
//        String userId= WxUtil.getOpenId(seller.getWxCode());
        if (StringUtils.isEmpty(seller.getUserId())){
            return BaseResult.parameterError();
        }
        Integer validByUserResult=sellerMapper.validByUserId(seller.getUserId());
        if (validByUserResult>0){
            return BaseResult.error("ERROR","不可重复创建店铺");
        }
        if (seller == null){
            return BaseResult.parameterError();
        }
        Integer validNameResult=sellerMapper.validByName(seller.getSellerName());
        if (validNameResult>0){
            return BaseResult.error("ERROR","店铺名称已存在");
        }
        Integer result=sellerMapper.add(seller);
        if (result <= 0){
            return BaseResult.error("ERROR","新增店铺失败");
        }
        return BaseResult.success("新增店铺成功");
    }

    /**
     * 删除店铺
     * @param id
     * @return
     */
    @Override
    public BaseResult delete(Long id) {
        if (StringUtils.isEmpty(id.toString())){
            return BaseResult.parameterError();
        }
        int result=sellerMapper.delete(id);
        if (result<=0){
            return BaseResult.error("ERROR","删除失败");
        }
        return BaseResult.success("删除成功");
    }

    /**
     * 修改店铺信息
     * @param seller
     * @return
     */
    @Override
    public BaseResult modify(Seller seller) {
        int result=sellerMapper.modify(seller);
        if (result<=0){
            return BaseResult.error("ERROR","修改失败");
        }
        return BaseResult.success("修改成功");
    }
}

