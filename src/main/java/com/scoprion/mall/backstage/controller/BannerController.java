package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.Banner;
import com.scoprion.mall.backstage.service.banner.BannerService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@RestController
@RequestMapping("/backstage/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * banner创建
     *
     * @param banner
     * @return
     */
    @Access
    @ApiOperation(value = "banner创建")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Banner banner) {
        return bannerService.add(banner);
    }


    /**
     * 分页查询banner列表  关键字搜索
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Access
    @ApiOperation(value = "关键字搜索")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResult list(int pageNo, int pageSize, String searchKey) {
        return bannerService.listByPage(pageNo, pageSize, searchKey);
    }

    /**
     * 首页展示轮播
     *
     * @return
     */
    @Access
    @ApiOperation(value = "首页展示轮播")
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
    @Access
    @ApiOperation(value = "编辑广告")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody Banner banner) {
        return bannerService.modify(banner);
    }

    /**
     * 删除banner
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation(value = "删除广告")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return bannerService.deleteById(id);
    }

    /**
     * banner详情
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation("banner详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return bannerService.findById(id);
    }

    /**
     * 批量修改广告状态
     * 状态  NORMAL, 正常,UN_NORMAL, 非正常
     *
     * @param jsonObject
     * @return
     */
    @Access
    @ApiOperation(value = "批量修改广告状态")
    @RequestMapping(value = "/batchModifyStatus", method = RequestMethod.POST)
    public BaseResult batchModifyStatus(@RequestBody JSONObject jsonObject) {
        String status = jsonObject.getString("status");
        List<Long> idList = jsonObject.getJSONArray("idList").toJavaList(Long.class);
        return bannerService.batchModifyStatus(status, idList);
    }
}
