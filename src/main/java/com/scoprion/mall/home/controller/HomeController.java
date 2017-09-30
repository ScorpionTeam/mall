package com.scoprion.mall.home.controller;

import com.scoprion.mall.banner.service.BannerService;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created on 2017/9/26.
 */
@Controller
public class HomeController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private BannerService bannerService;

    /**
     * 首页组装 数据
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap map) {
        List<Good> limit4 = goodService.findLimit4ByTimeGoods();
        List<Banner> homeBanner = bannerService.homeBanner();
        map.addAttribute("limit4", limit4);
        map.addAttribute("homeBanner", homeBanner);
        return "home";
    }

    @RequestMapping(value = "/backstage", method = RequestMethod.GET)
    public String backstage() {
        return "backstage/b-home";
    }


}
