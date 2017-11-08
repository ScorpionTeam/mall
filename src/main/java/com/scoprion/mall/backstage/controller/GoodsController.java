package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.backstage.service.good.GoodsService;
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
        Goods goods = object.getObject("goods", Goods.class);
        JSONArray jsonArray = object.getJSONArray("imageList");
        List<String> imgList = new ArrayList<>();
        for (Object obj : jsonArray) {
            imgList.add((String) obj);
        }
        goods.setImgUrlList(imgList);
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
     * @param goods
     * @return
     */
    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult updateGood(@RequestBody Goods goods) {
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

    /**
     * 模拟商品信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mock-good", method = RequestMethod.GET)
    public BaseResult mockGood() throws Exception {
        Goods goods = new Goods();
        goods.setGoodNo(String.valueOf(IDWorker.getFlowIdWorkerInstance().nextId()));
        goods.setGoodName("【韩国代购】the  SAEM 得鲜 爱可按钮唇膏 M系列 哑光系列 2克/支");
        goods.setCategoryId(100001L);
        goods.setDescription(
                "【富含葡萄籽油、坚果籽油、橄榄果油，质地丝滑柔顺，均匀色调，不易脱色，长效持久，有效锁住唇部水分，精华滋养改善干裂，淡化唇部细纹。tips：唇色不同，上色效果不同，详情图仅供参考哦。");
        goods.setPromotion(new BigDecimal(79.9));
        goods.setPrice(new BigDecimal(109.9));
        goods.setSaleVolume(300);
        goods.setDiscount(7);
        goods.setStock(1000);
        goods.setIsOnSale("1");
        goods.setIsHot("1");
        goods.setIsNew("1");
        goods.setIsFreight("0");
        goods.setBrandId(100000101L);
        goods.setSellerId(20000001L);
        goods.setVisitTotal(10000);
        return goodsService.add(goods);
    }

}
