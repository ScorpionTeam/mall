package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Good;
import com.scoprion.mall.backstage.service.good.GoodService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@RestController
@RequestMapping("/backstage/good")
public class GoodController {


    @Autowired
    private GoodService goodService;

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    @ApiOperation(value = "创建商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Good good) {
        return goodService.add(good);
    }

    /**
     * 根据商品id删除商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return goodService.deleteGoodById(id);
    }

    /**
     * 根据商品id查询商品
     *
     * @param id 商品id
     * @return
     */
    @ApiOperation(value = "根据商品id查询商品")
    @RequestMapping(value = "/goodInfo", method = RequestMethod.GET)
    public BaseResult goodInfo(Long id) {
        return goodService.goodInfo(id);
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
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey) {
        return goodService.findByCondition(pageNo, pageSize, searchKey);
    }

    /**
     * 修改商品
     *
     * @param good
     * @return
     */
    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(Good good) {
        return goodService.update(good);
    }


    /**
     * 商品上下架
     *
     * @param saleStatus 1上架 0下架 默认上架
     * @param goodId     商品id
     * @return
     */
    @ApiOperation(value = "商品上下架")
    @RequestMapping(value = "/modifySaleStatus", method = RequestMethod.GET)
    public BaseResult modifySaleStatus(String saleStatus, Long goodId) {
        return goodService.modifySaleStatus(saleStatus, goodId);
    }

    /**
     * 模拟商品信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mock-good", method = RequestMethod.GET)
    public BaseResult mockGood() throws Exception {
        Good good = new Good();
        good.setGoodNo(String.valueOf(IDWorker.getFlowIdWorkerInstance().nextId()));
        good.setGoodName("【韩国代购】the  SAEM 得鲜 爱可按钮唇膏 M系列 哑光系列 2克/支");
        good.setCategoryId(100001L);
        good.setDescription(
                "【富含葡萄籽油、坚果籽油、橄榄果油，质地丝滑柔顺，均匀色调，不易脱色，长效持久，有效锁住唇部水分，精华滋养改善干裂，淡化唇部细纹。tips：唇色不同，上色效果不同，详情图仅供参考哦。");
        good.setPromotion(new BigDecimal(79.9));
        good.setPrice(new BigDecimal(109.9));
        good.setSaleVolume(300);
        good.setDiscount(7);
        good.setStock(1000);
        good.setIsOnSale("1");
        good.setIsHot("1");
        good.setIsNew("1");
        good.setIsFreight("0");
        good.setBrandId(100000101L);
        good.setSellerId(20000001L);
        good.setVisitTotal(10000);
        return goodService.add(good);
    }

}