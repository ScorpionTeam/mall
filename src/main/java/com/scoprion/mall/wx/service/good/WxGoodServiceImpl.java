package com.scoprion.mall.wx.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.GoodSnapshot;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.wx.mapper.WxEstimateMapper;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by hmy
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
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findHomePage(Integer pageNo, Integer pageSize, Long categoryId) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Goods> page = wxGoodMapper.findHomePage(categoryId);
        return new PageResult(page);
    }

    /**
     * 商品详情
     *
     * @param goodId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult findById(Long goodId) {
        if (StringUtils.isEmpty(goodId.toString())) {
            return BaseResult.parameterError();
        }
        GoodExt goods = wxGoodMapper.findById(goodId);
        if (goods == null) {
            return BaseResult.notFound();
        }
        if (CommonEnum.OFF_SALE.getCode().equals(goods.getOnSale())) {
            return BaseResult.error("ERROR", "商品已经下架");
        }
        //获取图片列表
        List<MallImage> imgList = fileOperationMapper.findByTargetId(goods.getId(), 0);
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
    public PageResult findBySearchKey(Integer pageNo, Integer pageSize, Long categoryId, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Goods> page = wxGoodMapper.findBySearchKey(categoryId, searchKey);
        return new PageResult(page);
    }

    /**
     * 获取商品快照详情
     * @param orderId
     * @return
     */
    @Override
    public BaseResult findByGoodSnapshotDetail(Long orderId) {
        if(StringUtils.isEmpty(orderId.toString())) {
            return BaseResult.notFound();
        }
        GoodSnapshot goodSnapshot = wxGoodMapper.findByGoodSnapshotDetail(orderId);
        return BaseResult.success(goodSnapshot);
    }
}
