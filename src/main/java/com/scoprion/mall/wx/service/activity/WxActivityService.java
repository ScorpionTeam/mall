package com.scoprion.mall.wx.service.activity;

import com.scoprion.mall.domain.Goods;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created by fk
 * @create on 2017/11/12.
 */
public interface WxActivityService {

    /**
     * 抢购拼团
     * @param pageNo
     * @param pageSize
     * @return
     */

    PageResult group(int pageNo, int pageSize);
}
