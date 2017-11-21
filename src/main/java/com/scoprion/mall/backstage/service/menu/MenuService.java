package com.scoprion.mall.backstage.service.menu;

import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;

import java.awt.*;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:09
 */
public interface MenuService {
    BaseResult add(SysMenu sysMenu);

    BaseResult findByCondition();

    BaseResult modify(SysMenu sysMenu);

    BaseResult init(String userId);
}
