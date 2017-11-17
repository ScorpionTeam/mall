package com.scoprion.mall.wx.service.retroaction;

import com.scoprion.mall.domain.RetroAction;
import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/11/16.
 */
public interface WxRetroActionService {

    /**
     * 提交反馈意见
     *
     * @param retroAction
     * @return
     */
    BaseResult add(RetroAction retroAction);


}
