package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.backstage.service.banner.BannerService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@Controller
@RequestMapping("backstage/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * banner创建
     *
     * @param banner
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Banner banner) {
        return bannerService.add(banner);
    }


    /**
     * 分页查询banner列表  关键字搜索
     *
     * @param pageNo
     * @param pageSize
     * @param bannerName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResult list(int pageNo, int pageSize, String bannerName) {
        return bannerService.listByPage(pageNo, pageSize, bannerName);
    }

    /**
     * 首页展示轮播
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/homeShow", method = RequestMethod.GET)
    public BaseResult homeShow() {
        return bannerService.homeShow();
    }

    /**
     * 编辑banner
     *
     * @param banner
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(Banner banner) {
        return bannerService.modify(banner);
    }

    /**
     * 删除banner
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return bannerService.deleteById(id);
    }

}
