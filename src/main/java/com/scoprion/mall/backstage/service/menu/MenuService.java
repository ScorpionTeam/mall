package com.scoprion.mall.backstage.service.menu;

import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.awt.*;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:09
 */
public interface MenuService {
    BaseResult add(SysMenu sysMenu);

    PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey);

    BaseResult modify(SysMenu sysMenu);

    PageResult init(String userId);

    BaseResult findById(Long id);

    BaseResult deleteById(Long id);

    BaseResult list(String userId);
}
