package com.scoprion.mall.backstage.service.role;

import com.scoprion.mall.domain.SysRole;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:43
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public BaseResult add(SysRole sysRole) {
        return null;
    }

    @Override
    public BaseResult modify(SysRole sysRole) {
        return null;
    }

    @Override
    public BaseResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
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

    @Override
    public BaseResult allocationMenu(Long roleId, List<Long> menusId) {
        return null;
    }

    @Override
    public BaseResult bindRole(Long userId, Long roleId) {
        return null;
    }
}
