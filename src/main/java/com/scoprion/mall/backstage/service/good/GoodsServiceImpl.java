package com.scoprion.mall.backstage.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.backstage.mapper.GoodsMapper;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/9/29.
 * 运营后台商品控制器
 *
 * @author adming
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileOperationMapper fileOperationMapper;


    /**
     * 首页展示4件 限时购买商品
     *
     * @return
     */
    @Override
    public List<Goods> findLimit4ByTimeGoods() {
        return goodsMapper.findLimit4ByTimeGoods();
    }

    /**
     * 查询限时购买商品  分页展示
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @return
     */
    @Override
    public PageResult findByPageAndLimit(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Goods> page = goodsMapper.findByPageAndLimit();
        return new PageResult(page);
    }

    /**
     * 创建商品
     *
     * @param goods
     * @return
     */
    @Override
    public BaseResult add(GoodExt good) {
        int result = goodsMapper.add(good);
        if (result > 0) {
            //更新图片信息
            List<MallImage> imgList = good.getImgList();
            if (imgList != null && imgList.size() > 0) {
                for (MallImage mallImage : imgList) {
                    mallImage.setGoodId(good.getId());
                    fileOperationMapper.add(mallImage);
                }
            }
            return BaseResult.success("创建商品成功");
        }
        return BaseResult.error("mock_fail", "创建商品失败");
    }

    /**
     * 优选商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult preferenceGiven(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Goods> page = goodsMapper.preferenceGivenByPage();
        return new PageResult(page);
    }

    /**
     * 根据id查询商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public BaseResult findByGoodId(Long goodsId) {
        GoodExt goods = goodsMapper.findById(goodsId);
        if (null == goods) {
            return BaseResult.notFound();
        }
        //获取图片列表
        List<MallImage> imgList = fileOperationMapper.findByCondition(goods.getId(), 0);
        goods.setImgList(imgList);
        return BaseResult.success(goods);
    }

    /**
     * 根据id修改商品信息
     *
     * @param goods Goods
     * @return
     */
    @Override
    public BaseResult updateGood(GoodExt goods) {
        if (goods.getId() == null) {
            return BaseResult.parameterError();
        }
        goodsMapper.updateGoods(goods);
        List<MallImage> imgList = goods.getImgList();
        if (imgList != null && imgList.size() > 0) {
            //清空原来的图片
            for (MallImage mallImage : imgList) {
                fileOperationMapper.deleteById(mallImage.getId());
            }
            //插入图片
            for (MallImage mallImage : imgList) {
                mallImage.setGoodId(goods.getId());
                fileOperationMapper.add(mallImage);
            }
        }
        return BaseResult.success("修改成功");
    }

    /**
     * 条件查询商品列表分页
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey  模糊信息
     * @param goodNo     商品编号
     * @param saleStatus 上下架
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param categoryId 类目
     * @param isHot      热销
     * @param isNew      新品
     * @param isFreight  包邮
     * @param brandId    品牌
     * @return
     */
    @Override
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey, String goodNo, String saleStatus,
                                      String startDate, String endDate, Long categoryId, String isHot, String isNew,
                                      String isFreight, Long brandId) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Goods> page = goodsMapper.findByCondition(searchKey, goodNo, saleStatus, startDate, endDate, categoryId,
                isHot, isNew, isFreight, brandId);
        if (page == null) {
            return new PageResult(new ArrayList<Goods>());
        }
        return new PageResult(page);
    }

    /**
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param goodsId    商品id
     * @return
     */
    @Override
    public BaseResult modifySaleStatus(String saleStatus, Long goodsId) {
        if (StringUtils.isEmpty(saleStatus) || null == goodsId) {
            return BaseResult.parameterError();
        }
        if (!Constant.STATUS_01.contains(saleStatus)) {
            return BaseResult.error("parameterError", "上下架状态不正确");
        }
        List<Long> idList = new ArrayList<>();
        idList.add(goodsId);
        int result = goodsMapper.batchModifySaleStatus(saleStatus, idList);
        if (result > 0) {
            return BaseResult.success(Constant.ON_SALE.equals(saleStatus) ? "商品上架成功" : "商品下架成功");
        }
        return BaseResult.error("006", Constant.ON_SALE.equals(saleStatus) ? "商品上架失败" : "商品下架失败");
    }

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    @Override
    public BaseResult deleteGoodsById(Long id) {
        Goods goods = goodsMapper.findById(id);
        if (Constant.ON_SALE.equals(goods.getIsOnSale())) {
            return BaseResult.error("del_error", "删除失败，商品未下架，不能删除");
        }
        List<Long> idList = new ArrayList<>();
        idList.add(id);
        int result = goodsMapper.batchDeleteGood(idList);
        if (result > 0) {
            return BaseResult.success("删除商品成功");
        }
        return BaseResult.error("sysError", "删除商品失败");
    }

    /**
     * 批量删除商品
     *
     * @param idList 商品id集合
     * @return
     */
    @Override
    public BaseResult batchDeleteGood(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        int result = goodsMapper.batchDeleteGood(idList);
        if (result == 0) {
            return BaseResult.error("delete_error", "商品未下架，不能删除");
        }
        if (idList.size() > result) {
            return BaseResult.success("部分商品未下架，不能删除，其余的已经删除成功");
        }
        return BaseResult.success("删除成功");
    }

    @Override
    public BaseResult modifyGoodsDeduction(Long id, Integer count) {
        int result = goodsMapper.modifyGoodsDeduction(id, count);
        if (result > 0) {
            return BaseResult.success("修改成功");
        }
        return BaseResult.error("modify-error", "修改失败");
    }

    /**
     * 批量商品上下架
     *
     * @param saleStatus  1上架 0下架 默认上架
     * @param goodsIdList 商品id集合
     * @return
     */
    @Override
    public BaseResult batchModifySaleStatus(String saleStatus, List<Long> goodsIdList) {
        if (goodsIdList == null || goodsIdList.size() == 0) {
            return BaseResult.parameterError();
        }
        if (!Constant.STATUS_01.contains(saleStatus)) {
            return BaseResult.parameterError();
        }
        goodsMapper.batchModifySaleStatus(saleStatus, goodsIdList);
        return BaseResult.success(Constant.ON_SALE.equals(saleStatus) ? "商品批量上架成功" : "商品批量下架成功");
    }
}
