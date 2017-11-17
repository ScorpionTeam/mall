package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.RetroAction;
import com.scoprion.result.BaseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/11/16.
 */
@RestController
@RequestMapping("retroAction")
public class RetroActionController {


    /**
     * 意见反馈
     * @param retroAction
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody RetroAction retroAction) {
        return null;

    }

}
