package com.scoprion.mall.wx.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Estimate;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.wx.mapper.WxEstimateMapper;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
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

    @Autowired
    private WxEstimateMapper wxEstimateMapper;

    /**
     * 首页商品列表
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

    /**
     * 查询商品评价列表
     *
     * @param pageNo
     * @param pageSize
     * @param goodId
     * @param wxCode
     * @return
     */
    @Override
    public BaseResult findEstimate(Integer pageNo, Integer pageSize,
                                   Long goodId, String wxCode) {
        PageHelper.startPage(pageNo, pageSize);
        if (pageNo == null || pageSize == null || goodId == null) {
            return BaseResult.parameterError();
        }
        if (StringUtils.isEmpty(wxCode)) {
            return BaseResult.parameterError();
        }
        String userId = WxUtil.getOpenId(wxCode);
        Page<Estimate> page = wxEstimateMapper.findPage(goodId, userId);
        return BaseResult.success(page);
    }
}
