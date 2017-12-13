package com.scoprion.mall.seller.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.CategoryGoodMapper;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.request.GoodRequestParams;
import com.scoprion.mall.seller.mapper.SellerGoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.DateParamFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-08 10:27
 */
@Service
public class SellerGoodServiceImpl implements SellerGoodService {
    @Autowired
    private SellerGoodMapper sellerGoodMapper;

    @Autowired
    private GoodLogMapper goodLogMapper;

    @Autowired
    private FileOperationMapper fileOperationMapper;

    @Autowired
    CategoryGoodMapper categoryGoodMapper;

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult add(GoodExt good) {
        int result = sellerGoodMapper.add(good);
        if (result > 0) {
            //更新图片信息
            List<MallImage> imgList = good.getImgList();
            if (imgList != null && imgList.size() > 0) {
                for (MallImage mallImage : imgList) {
                    mallImage.setGoodId(good.getId());
                    fileOperationMapper.add(mallImage);
                }
            }
            if (good.getCategoryId() != null) {
                bindCategoryGood(good.getCategoryId(), good.getId());
            }
            saveGoodLog(good.getGoodName(), "新增商品", good.getId());
            return BaseResult.success("新增成功");
        }
        return BaseResult.error("add_error", "新增商品失败");
    }

    /**
     * 商品绑定类目
     *
     * @param categoryId
     * @param goodId
     */
    private void bindCategoryGood(Long categoryId, Long goodId) {
        categoryGoodMapper.bindCategoryGood(categoryId, goodId);
    }

    /**
     * 商品解绑类目
     *
     * @param goodId
     */
    private void unbindCategoryGood(Long goodId) {
        if (goodId == null) {
            return;
        }
        categoryGoodMapper.unbindWithGoodId(goodId);
    }

    /**
     * 保持商品操作日志
     *
     * @param goodName
     * @param action
     * @param goodId
     */
    private void saveGoodLog(String goodName, String action, Long goodId) {
        ServiceCommon.saveGoodLog(goodName, action, goodId, goodLogMapper);
    }

    /**
     * 根据id查询商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public BaseResult findById(Long goodsId) {
        GoodExt good = sellerGoodMapper.findById(goodsId);
        if (good == null) {
            return BaseResult.notFound();
        }
        //获取图片列表
        List<MallImage> imgList = fileOperationMapper.findByTargetId(good.getId(), 0);
        good.setImgList(imgList);
        return BaseResult.success(good);
    }

    /**
     * 根据id修改商品信息
     *
     * @param good 商品信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult updateGood(GoodExt good) {
        if (good.getId() == null) {
            return BaseResult.parameterError();
        }
        GoodExt localGood = sellerGoodMapper.findById(good.getId());
        if (localGood == null) {
            return BaseResult.error("update_error", "未找到商品");
        }
        if (!StringUtils.isEmpty(localGood.getOnSale()) &&
                CommonEnum.ON_SALE.getCode().equals(localGood.getOnSale())) {
            //上架状态，不能修改
            return BaseResult.error("update_error", "商品为上架状态，不能修改");
        }
        if (good.getCategoryId() == null && localGood.getCategoryId() != null) {
            //类目解绑
            unbindCategoryGood(localGood.getId());
        } else if (!good.getCategoryId().equals(localGood.getCategoryId())) {
            //类目解绑
            unbindCategoryGood(localGood.getId());
            //绑定类目
            bindCategoryGood(good.getCategoryId(), good.getId());
        }

        sellerGoodMapper.updateGood(good);
        List<MallImage> imgList = good.getImgList();
        if (imgList != null && imgList.size() > 0) {
            //清空原来的图片
            for (MallImage mallImage : imgList) {
                fileOperationMapper.deleteById(mallImage.getId());
            }
            //插入图片
            for (MallImage mallImage : imgList) {
                mallImage.setGoodId(good.getId());
                fileOperationMapper.add(mallImage);
            }
        }
        saveGoodLog(good.getGoodName(), "修改商品信息", good.getId());
        return BaseResult.success("修改成功");
    }


    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    @Override
    public BaseResult deleteGoodById(Long id) {
        Goods goods = sellerGoodMapper.findById(id);
        if (CommonEnum.ON_SALE.getCode().equals(goods.getOnSale())) {
            return BaseResult.error("delete_error", "删除失败，商品未下架，不能删除");
        }
        List<Long> idList = new ArrayList<>();
        idList.add(id);
        int result = sellerGoodMapper.batchDeleteGood(idList);
        if (result > 0) {
            return BaseResult.success("删除商品成功");
        }
        saveGoodLog(goods.getGoodName(), "删除商品", goods.getId());
        return BaseResult.error("ERROR", "删除商品失败");
    }

    /**
     * 批量删除商品
     *
     * @param idList 商品id集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult batchDeleteGood(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        int result = sellerGoodMapper.batchDeleteGood(idList);
        if (result == 0) {
            return BaseResult.error("batch_error", "商品未下架，不能删除");
        }
        if (idList.size() > result) {
            return BaseResult.success("部分商品未下架，不能删除，其余的已经删除成功");
        }
        idList.forEach(goodId -> saveGoodLog("", "批量删除商品", goodId));
        return BaseResult.success("删除成功");
    }

    @Override
    public BaseResult updateGoodStock(Long id, Integer count) {
        int result = sellerGoodMapper.updateGoodStock(id, count);
        if (result > 0) {
            GoodExt good = sellerGoodMapper.findById(id);
            saveGoodLog(good.getGoodName(), "修改商品库存", good.getId());
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("update_stock_error", "修改失败");
    }

    /**
     * 批量商品上下架
     *
     * @param saleStatus  1上架 0下架 默认上架 上下ON_SALE", "上架 OFF_SALE", "下架"),
     * @param goodsIdList 商品id集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult batchModifySaleStatus(String saleStatus, List<Long> goodsIdList) {
        if (goodsIdList == null || goodsIdList.size() == 0) {
            return BaseResult.parameterError();
        }
        if (!CommonEnum.ON_SALE.getCode().equals(saleStatus) && !CommonEnum.OFF_SALE.getCode().equals(saleStatus)) {
            return BaseResult.parameterError();
        }
        Integer result = sellerGoodMapper.batchModifySaleStatus(saleStatus, goodsIdList);
        String action = CommonEnum.ON_SALE.getCode().equals(saleStatus) ? "商品批量上架" : "商品批量下架";
        goodsIdList.forEach(id -> saveGoodLog("", action, id));
        return BaseResult.success(action + "成功");
    }


    @Override
    public PageResult findByCondition(GoodRequestParams params) {
        if (params.getSellerId() == null) {
            return new PageResult();
        }
        PageHelper.startPage(params.getPageNo(), params.getPageSize());
        if (StringUtils.isEmpty(params.getSearchKey())) {
            params.setSearchKey(null);
        }
        if (!StringUtils.isEmpty(params.getSearchKey())) {
            params.setSearchKey("%" + params.getSearchKey() + "%");
        }
        params.setEndDate(DateParamFormatUtil.formatDate(params.getEndDate()));
        params.setStartDate(DateParamFormatUtil.formatDate(params.getStartDate()));
        Page<GoodExt> page;
        if (CommonEnum.UNBIND_CATEGORY.getCode().equals(params.getType())) {
            //未绑定类目的商品列表
            page = sellerGoodMapper.findForCategory(params);
        } else if (CommonEnum.BIND_ACTIVITY.getCode().equals(params.getType())) {
            //已经绑定活动的商品列表搜索
            page = sellerGoodMapper.findByActivityId(params);
        } else if (CommonEnum.UNBIND_ACTIVITY.getCode().equals(params.getType())) {
            //未绑定活动的商品列表
            page = sellerGoodMapper.findForActivity(params);
        } else {
            //基础条件查询
            page = sellerGoodMapper.findByCondition(params);
        }
        return new PageResult(page);
    }
}
