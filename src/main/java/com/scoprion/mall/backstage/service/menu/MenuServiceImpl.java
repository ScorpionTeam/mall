package com.scoprion.mall.backstage.service.menu;

import com.scoprion.mall.domain.SysMenu;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Service;

 

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:15
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Override
    public BaseResult add(SysMenu sysMenu) {
        return null;
    }

    @Override
    public BaseResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        return null;
    }

    @Override
    public BaseResult modify(SysMenu sysMenu) {
        return null;
    }

    @Override
    public BaseResult init(String userId) {
        return null;
    }

    @Override
    public BaseResult findById(Long id) {
        return null;
    }

    @Override
    public BaseResult deleteById(Long id) {
        return null;
    }
}
