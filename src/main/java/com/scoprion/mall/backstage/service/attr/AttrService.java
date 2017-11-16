package com.scoprion.mall.backstage.service.attr;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.result.BaseResult;

/**
 * @author by kunlun
 * @created on 2017/11/16.
 */
public interface AttrService {

    /**
     * 创建规格
     *
     * @param json
     * @return
     */
    BaseResult add(JSONObject json);
}
