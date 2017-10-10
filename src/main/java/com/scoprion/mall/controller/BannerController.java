package com.scoprion.mall.controller;

import com.scoprion.mall.domain.Banner;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("banner")
public class BannerController {


    /**
     * banner创建
     *
     * @param banner
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Banner banner) {

        return null;
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
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public PageResult init(int pageNo, int pageSize, String bannerName) {
        return null;
    }

    /**
     * 首页展示轮播
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/homeShow", method = RequestMethod.GET)
    public BaseResult homeShow() {
        return null;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public BaseResult edit(Banner banner){
        return null;
    }

    /**
     * 删除banner
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    public BaseResult deleteByPrimaryKey(Long id){
        return null;
    }

}
