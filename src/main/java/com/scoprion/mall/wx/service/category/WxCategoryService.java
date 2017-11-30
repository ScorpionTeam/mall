package com.scoprion.mall.wx.service.category;

import com.scoprion.mall.domain.request.PageRequestParams;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Service;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-30 09:50
 */
@Service
public interface WxCategoryService {
    /**
     * 首页类目列表
     *
     * @param pageNo
     * @param pageSize
     * @param type
     * @return
     */
    PageResult findHomePage(Integer pageNo, Integer pageSize, String type);
}
