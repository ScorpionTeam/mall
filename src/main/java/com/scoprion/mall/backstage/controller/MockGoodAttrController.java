package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.mock.MockGoodAttrService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟商品规格
 *
 * @author by kunlun
 * @created on 2017/11/15.
 */
@RestController
@RequestMapping("mock")
public class MockGoodAttrController {

    @Autowired
    private MockGoodAttrService mockGoodAttrService;

    /**
     * 模拟创建商品规格属性 属性值
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public BaseResult add() {
        return mockGoodAttrService.add();
    }

    /**
     * 查询商品规格属性 属性值
     *
     * @param goodId
     * @return
     */
    @RequestMapping(value = "/findAttr", method = RequestMethod.GET)
    public BaseResult findAttr(Long goodId) {
        return mockGoodAttrService.findAttr(goodId);
    }


}
