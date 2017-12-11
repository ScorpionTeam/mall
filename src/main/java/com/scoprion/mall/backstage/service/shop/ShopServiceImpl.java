package com.scoprion.mall.backstage.service.shop;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.ShopMapper;
import com.scoprion.mall.domain.Seller;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */
@Service
public class ShopServiceImpl implements ShopService{


    @Autowired
    private ShopMapper shopMapper;


    /**
     * 商铺列表
     * @param pageNo
     * @param pageSize
     * @param audit
     * @return
     */
    @Override
    public PageResult listPage(Integer pageNo, Integer pageSize,String audit) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Seller>page=shopMapper.listPage(audit);
        return new PageResult(page);
    }
}
