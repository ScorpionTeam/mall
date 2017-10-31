package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Good;
import com.scoprion.mall.backstage.service.good.GoodService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("good")
public class GoodController {


    @Autowired
    private GoodService goodService;

    /**
     * 查询限时购买商品  分页展示
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/limit-by-time", method = RequestMethod.GET)
    public PageResult findByPageAndLimit(int pageNo, int pageSize) {
        return goodService.findByPageAndLimit(pageNo, pageSize);
    }

    /**
     * 创建商品
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Good good) {
        return goodService.add(good);
    }


    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/preference-given", method = RequestMethod.GET)
    public PageResult preferenceGiven(int pageNo, int pageSize) {
        return goodService.preferenceGiven(pageNo, pageSize);
    }


    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/good-info", method = RequestMethod.GET)
    public BaseResult goodInfo(Long goodId) {
        return goodService.goodInfo(goodId);
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
