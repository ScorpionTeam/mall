package com.scoprion.mall.littlesoft.service.goods;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.littlesoft.mapper.WxGoodsMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Administrator
 * @created on 2017/11/6/006.
 */

@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    private WxGoodsMapper wxGoodsMapper;
    /**
     * 商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAll (Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Good>page=wxGoodsMapper.findAll();
        return new PageResult(page);
    }

    /**
     * 根据商品id获取商品详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult goodsDetail(Long id) {
        if(StringUtils.isEmpty(id.toString())){
            return BaseResult.error("fail_detail","请指定商品");
        }
        Good result=wxGoodsMapper.goodsDetail(id);
        return BaseResult.success(result);
    }
}
