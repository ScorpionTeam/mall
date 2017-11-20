package com.scoprion.mall.wx.service.free;

import com.scoprion.result.PageResult;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
public interface FreeService {

    PageResult findAll(int pageNo, int pageSize);
}
