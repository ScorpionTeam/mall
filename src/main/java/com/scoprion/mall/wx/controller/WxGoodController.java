package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.wx.service.good.WxGoodService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by hmy
 * @created on 2017/11/6/006.
 */

@RestController
@RequestMapping("wx/good")
public class WxGoodController {

    @Autowired
    private WxGoodService wxGoodService;

    /**
     * 首页商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Access
    @RequestMapping(value = "/findHomePage", method = RequestMethod.GET)
    public PageResult findHomePage(Integer pageNo, Integer pageSize, Long categoryId) {
        return wxGoodService.findHomePage(pageNo, pageSize, categoryId);
    }

    /**
     * 查询商品详情
     *
     * @param goodId
     * @return
     */
    @Access
    @RequestMapping(value = "/findByGoodId", method = RequestMethod.GET)
    public BaseResult findByGoodId(Long goodId) {
        return wxGoodService.findById(goodId);
    }

    /**
     * 查询商品评价列表
     *
     * @param pageNo
     * @param pageSize
     * @param goodId
     * @return
     */
    @Access
    @RequestMapping(value = "/findEstimate", method = RequestMethod.GET)
    public BaseResult findEstimate(Integer pageNo, Integer pageSize,
                                   Long goodId) {
        return wxGoodService.findEstimate(pageNo, pageSize, goodId);
    }


    /**
     * 商品搜索
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Access
    @RequestMapping(value = "/findBySearchKey", method = RequestMethod.GET)
    public PageResult findBySearchKey(Integer pageNo, Integer pageSize, Long categoryId, String searchKey) {
        return wxGoodService.findBySearchKey(pageNo, pageSize, categoryId, searchKey);
    }

    /**
     * 获取商品快照详情
     * @param orderId
     * @return
     */
    @Access
    @RequestMapping(value = "/findByGoodSnapshotDetail", method = RequestMethod.GET)
    public BaseResult findByGoodSnapshotDetail(Long orderId) {
        return wxGoodService.findByGoodSnapshotDetail(orderId);
    }

}