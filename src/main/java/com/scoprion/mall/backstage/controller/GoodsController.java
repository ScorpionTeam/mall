package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.backstage.service.good.GoodsService;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycj
 * @created on 2017/9/29.
 */
@RestController
@RequestMapping("/backstage/good")
public class GoodsController {


    private static final String IMAGE_LIST = "imageList";

    @Autowired
    private GoodsService goodsService;

    /**
     * 创建商品
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "创建商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody JSONObject object) {
        GoodExt goods = object.getObject("good", GoodExt.class);
        if (!object.containsKey(IMAGE_LIST)) {
            return BaseResult.error("add_error", "图片为必传信息");
        }
        List<MallImage> imgList = object.getJSONArray(IMAGE_LIST).toJavaList(MallImage.class);
        goods.setImgList(imgList);
        return goodsService.add(goods);
    }

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return goodsService.deleteGoodsById(id);
    }

    /**
     * 批量删除商品
     *
     * @return
     */
    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/batchDeleteGood", method = RequestMethod.POST)
    public BaseResult batchDeleteGood(@RequestBody JSONObject jsonObject) {
        List<Long> idList = jsonObject.getJSONArray("idList").toJavaList(Long.class);
        return goodsService.batchDeleteGood(idList);
    }

    /**
     * 根据商品id查询商品
     *
     * @param id 商品id
     * @return
     */
    @ApiOperation(value = "根据商品id查询商品")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findByGoodId(Long id) {
        return goodsService.findByGoodId(id);
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
    @ApiOperation(value = "查询商品")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey, String goodNo, String saleStatus,
                                      String startDate, String endDate, Long categoryId, String isHot, String isNew,
                                      String isFreight, Long brandId, Long activityId) {
        return goodsService.findByCondition(pageNo, pageSize, searchKey, goodNo, saleStatus, startDate, endDate,
                categoryId, isHot, isNew, isFreight, brandId, activityId);
    }

    /**
     * 修改商品
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult updateGood(@RequestBody JSONObject object) {
        GoodExt goods = object.getObject("good", GoodExt.class);
        if (object.containsKey(IMAGE_LIST)) {
            List<MallImage> imageList = object.getJSONArray(IMAGE_LIST).toJavaList(MallImage.class);
            goods.setImgList(imageList);
        }
        return goodsService.updateGood(goods);
    }


    /**
     * 商品上下架
     *
     * @param saleStatus 1上架 0下架 默认上架
     * @param id         商品id
     * @return
     */
    @ApiOperation(value = "商品上下架")
    @RequestMapping(value = "/modifySaleStatus", method = RequestMethod.GET)
    public BaseResult modifySaleStatus(String saleStatus, Long id) {
        return goodsService.modifySaleStatus(saleStatus, id);
    }

    /**
     * 批量商品上下架
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "批量商品上下架")
    @RequestMapping(value = "/batchModifySaleStatus", method = RequestMethod.POST)
    public BaseResult batchModifySaleStatus(@RequestBody JSONObject object) {
        //1上架 0下架 默认上架
        String saleStatus = object.getString("saleStatus");
        //商品id集合
        List<Long> goodsIdList = object.getJSONArray("goodsIdList").toJavaList(Long.class);
        return goodsService.batchModifySaleStatus(saleStatus, goodsIdList);
    }

    /**
     * 商品库存修改
     *
     * @param count 数量  小于0 扣减，大于0 增加库存
     * @param id    商品id，主键
     * @return
     */
    @ApiOperation(value = "商品库存修改")
    @RequestMapping(value = "/modifyGoodDeduction", method = RequestMethod.GET)
    public BaseResult modifyGoodsDeduction(Long id, Integer count) {
        return goodsService.modifyGoodsDeduction(id, count);
    }


}
