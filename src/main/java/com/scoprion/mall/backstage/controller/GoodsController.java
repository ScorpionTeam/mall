package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.backstage.service.good.GoodsService;
import com.scoprion.mall.domain.GoodsImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@RestController
@RequestMapping("/backstage/good")
public class GoodsController {


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
        List<String> imageList = object.getJSONArray("imageList").toJavaList(String.class);
        if (imageList != null && imageList.size() > 0) {
            List<GoodsImage> imgList = new ArrayList<>();
            for (String url : imageList) {
                imgList.add(new GoodsImage(url));
            }
            goods.setImgList(imgList);
        }
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
    @RequestMapping(value = "/bathDeleteGoods", method = RequestMethod.POST)
    public BaseResult bathDeleteGoods(@RequestBody JSONObject jsonObject) {
        List<Long> idList = jsonObject.getJSONArray("idList").toJavaList(Long.class);
        return goodsService.bathDeleteGoods(idList);
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
     * @param searchKey
     * @return
     */
    @ApiOperation(value = "查询商品")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey) {
        return goodsService.findByCondition(pageNo, pageSize, searchKey);
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
        List<String> imageList = object.getJSONArray("imageList").toJavaList(String.class);
        if (imageList != null) {
            List<GoodsImage> imgList = new ArrayList<>();
            for (String url : imageList) {
                imgList.add(new GoodsImage(url));
            }
            goods.setImgList(imgList);
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
    @RequestMapping(value = "/bathModifySaleStatus", method = RequestMethod.POST)
    public BaseResult bathModifySaleStatus(@RequestBody JSONObject object) {
        //1上架 0下架 默认上架
        String saleStatus = object.getString("saleStatus");
        //商品id集合
        List<Long> goodsIdList = object.getJSONArray("goodsIdList").toJavaList(Long.class);
        return goodsService.bathModifySaleStatus(saleStatus, goodsIdList);
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
