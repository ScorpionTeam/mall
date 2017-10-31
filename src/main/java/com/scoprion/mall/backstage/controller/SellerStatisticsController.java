package com.scoprion.mall.backstage.controller;

import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/10/10.
 */
@Controller
@RequestMapping("sellerStatistics")
public class SellerStatisticsController {

    /**
     * 店铺累计访问量
     *
     * @param sellerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/totalVisit", method = RequestMethod.GET)
    public BaseResult totalVisit(Long sellerId) {
        return null;
    }

    /**
     * 店铺日访问量
     *
     * @param sellerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dayVisit", method = RequestMethod.GET)
    public BaseResult dayVisit(Long sellerId) {
        return null;
    }


    /**
     * 转化率
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/percentConversions", method = RequestMethod.GET)
    public BaseResult percentConversions() {
        return null;
    }


}
