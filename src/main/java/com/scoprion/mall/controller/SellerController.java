package com.scoprion.mall.controller;

import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
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

    /**
     * 运营管理   商家列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public PageResult init(int pageNo, int pageSize, String sellerName) {

        return null;
    }

    /**
     * 创建商户
     *
     * @param seller
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(Seller seller) {
        return null;
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
        return null;
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete() {
        return null;
    }


}
