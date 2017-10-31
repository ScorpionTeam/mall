package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.backstage.service.seller.SellerService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/10/10.
 */
@Controller
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 运营管理   店铺列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerName
     * @return
     */
    @ApiOperation(value = "分页查询店铺列表")
    @ResponseBody
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public PageResult init(int pageNo, int pageSize, String sellerName) {
        return sellerService.listByPage(pageNo, pageSize, sellerName);
    }

    /**
     * 创建商户
     *
     * @param seller
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Seller seller) throws Exception {
        return sellerService.add(seller);
    }

    /**
     * 编辑商户信息
     *
     * @param seller
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public BaseResult edit(Seller seller) {
        return sellerService.edit(seller);
    }


    /**
     * 根据主键删除商户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
    public BaseResult deleteByPrimaryKey(Long id) {
        return sellerService.deleteByPrimaryKey(id);
    }

    /**
     * 查询商户详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sellerInfo", method = RequestMethod.GET)
    public BaseResult sellerInfo(Long id) {
        return sellerService.sellerInfo(id);
    }


}
