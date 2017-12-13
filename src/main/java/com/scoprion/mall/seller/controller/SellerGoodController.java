package com.scoprion.mall.seller.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.request.GoodRequestParams;
import com.scoprion.mall.seller.service.good.SellerGoodService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <商户商品模块>
 * @date 2017-12-08 10:26
 */
@RestController
@RequestMapping("seller/good")
public class SellerGoodController {
    private static final String IMAGE_LIST = "imageList";

    @Autowired
    private SellerGoodService sellerGoodService;

    /**
     * 创建商品
     *
     * @param object
     * @return
     */
    @Access
    @ApiOperation(value = "创建商品")
    @PostMapping(value = "/add")
    public BaseResult add(@RequestBody JSONObject object) {
        GoodExt goods = object.getObject("good", GoodExt.class);
        List<MallImage> imgList = object.getJSONArray(IMAGE_LIST).toJavaList(MallImage.class);
        goods.setImgList(imgList);
        return sellerGoodService.add(goods);
    }

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    @Access
    @ApiOperation(value = "删除商品")
    @GetMapping(value = "/deleteById/{id}")
    public BaseResult deleteById(@PathVariable("id") Long id) {
        return sellerGoodService.deleteGoodById(id);
    }

    /**
     * 批量删除商品
     *
     * @return
     */
    @Access
    @ApiOperation(value = "批量删除商品")
    @PostMapping(value = "/batchDeleteGood")
    public BaseResult batchDeleteGood(@RequestBody JSONObject jsonObject) {
        List<Long> idList = jsonObject.getJSONArray("idList").toJavaList(Long.class);
        return sellerGoodService.batchDeleteGood(idList);
    }

    /**
     * 根据商品id查询商品
     *
     * @param id 商品id
     * @return
     */
    @Access
    @ApiOperation(value = "根据商品id查询商品")
    @GetMapping(value = "/findById/{id}")
    public BaseResult findById(@PathVariable("id") Long id) {
        return sellerGoodService.findById(id);

    }

    @Access
    @ApiOperation(value = "查询商品")
    @PostMapping(value = "/findByCondition")
    public PageResult findByCondition(@RequestBody GoodRequestParams goodRequestParams) {
        return sellerGoodService.findByCondition(goodRequestParams);
    }

    /**
     * 修改商品
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "修改商品")
    @PostMapping(value = "/update")
    public BaseResult updateGood(@RequestBody JSONObject object) {
        GoodExt goods = object.getObject("good", GoodExt.class);
        if (object.containsKey(IMAGE_LIST)) {
            List<MallImage> imageList = object.getJSONArray(IMAGE_LIST).toJavaList(MallImage.class);
            goods.setImgList(imageList);
        }
        return sellerGoodService.updateGood(goods);
    }


    /**
     * 批量商品上下架
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "批量商品上下架")
    @PostMapping(value = "/batchModifySaleStatus")
    public BaseResult batchModifySaleStatus(@RequestBody JSONObject object) {
        //1上架 0下架 默认上架
        String saleStatus = object.getString("saleStatus");
        //商品id集合
        List<Long> goodsIdList = object.getJSONArray("goodsIdList").toJavaList(Long.class);
        return sellerGoodService.batchModifySaleStatus(saleStatus, goodsIdList);
    }

    /**
     * 商品库存修改
     *
     * @param count 数量  小于0 扣减，大于0 增加库存
     * @param id    商品id，主键
     * @return
     */
    @ApiOperation(value = "商品库存修改")
    @GetMapping(value = "/updateGoodStock/{id}/{count}")
    public BaseResult updateGoodStock(@PathVariable("id") Long id,
                                      @PathVariable("count") Integer count) {
        return sellerGoodService.updateGoodStock(id, count);
    }

}
