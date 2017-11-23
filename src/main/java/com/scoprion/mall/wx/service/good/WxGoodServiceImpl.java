package com.scoprion.mall.wx.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.domain.Estimate;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.mall.wx.mapper.WxEstimateMapper;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FileOperationMapper fileOperationMapper;

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
        if (StringUtils.isEmpty(goodId.toString())) {
            return BaseResult.parameterError();
        }
        GoodExt goods = wxGoodMapper.findById(goodId);
        if (goods == null) {
            return BaseResult.notFound();
        }
        if (Constant.STATUS_ZERO.equals(goods.getIsOnSale())) {
            return BaseResult.error("not_enough_stock", "商品已经下架");
        }
        //获取图片列表
        List<MallImage> imgList = fileOperationMapper.findByCondition(goods.getId(), 0);
        goods.setImgList(imgList);
        wxGoodMapper.updateVisitTotal(goodId);
        return BaseResult.success(goods);
    }

    /**
     * 查询商品评价列表
     *
     * @param pageNo
     * @param pageSize
     * @param goodId
     * @return
     */
    @Override
    public BaseResult findEstimate(Integer pageNo, Integer pageSize,
                                   Long goodId) {
        PageHelper.startPage(pageNo, pageSize);
        if (pageNo == null || pageSize == null || goodId == null) {
            return BaseResult.parameterError();
        }
        Page<Estimate> page = wxEstimateMapper.findPage(goodId, null);
        return BaseResult.success(page);
    }

    /**
     * 商品搜索
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Override
    public PageResult findBySearchKey(Integer pageNo, Integer pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Goods> page = wxGoodMapper.findBySearchKey(searchKey);
        if (page == null) {
            return new PageResult(new ArrayList<Goods>());
        }
        return new PageResult(page);
    }
}
