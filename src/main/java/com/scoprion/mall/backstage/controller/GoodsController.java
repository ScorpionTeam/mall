package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.backstage.service.good.GoodsService;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.mall.domain.request.GoodRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Access
    @ApiOperation(value = "创建商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody JSONObject object) {
        GoodExt goods = object.getObject("good", GoodExt.class);
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
    @Access
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
    @Access
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
    @Access
    @ApiOperation(value = "根据商品id查询商品")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findByGoodId(Long id) {
        return goodsService.findByGoodId(id);
    }

    @Access
    @ApiOperation(value = "查询商品")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    public PageResult findByCondition(@RequestBody GoodRequestParams goodRequestParams) {
        return goodsService.findByCondition(goodRequestParams);
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

    /**
     * 新创建商品审核
     *
     * @param audit
     * @param reason
     * @param id
     * @return
     */
    @ApiOperation(value = "新创建商品审核")
    @PostMapping(value = "/auditGood")
    public BaseResult auditGood(String audit,
                                String reason,
                                Long id) {
        return goodsService.auditGood(audit, reason, id);
    }
}
