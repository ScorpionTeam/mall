package com.scoprion.mall.wx.service.good;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
@Service
public class WxGoodServiceImpl implements WxGoodService {

    @Autowired
    private WxGoodMapper wxGoodMapper;

    /**
     * 商品列表
     *
     * @param pageNo
     * @param PageSize
     * @return
     */
    @Override
    public PageResult findOnline(int pageNo, int PageSize) {
        PageHelper.startPage(pageNo, PageSize);
        Page<Goods> page = wxGoodMapper.findOnline();
        return new PageResult(page);
    }

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    @Override
    public BaseResult findById(Long goodId) {
        Goods goods = wxGoodMapper.findById(goodId);
        if (null == goods) {
            return BaseResult.notFound();
        }
        wxGoodMapper.updateVisitTotal(goodId);
        return BaseResult.success(goods);
    }

}
