package com.scoprion.mall.order;

import com.scoprion.annotation.AccessSecret;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    /**
     * 我的订单
     *
     * @return
     */
    @AccessSecret
    @RequestMapping(value = "/my-order", method = RequestMethod.GET)
    public String myOrder() {
        return "my-order";
    }

    /**
     * 查询订单列表
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @AccessSecret
    @ResponseBody
    @RequestMapping(value = "/order-list", method = RequestMethod.GET)
    public PageResult all(int pageNo, int pageSize, HttpServletRequest request) {
        return null;
    }

}
