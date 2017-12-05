package com.scoprion.mall.wx.rabbitmq;

import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/12/4.
 */
public interface SendService {

    /**
     * 发送信息
     *
     * @param message
     * @return
     */
    BaseResult sendMessage(String message);
}
