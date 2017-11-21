package com.scoprion.mall.backstage.service.role;

import com.scoprion.mall.domain.SysRole;
import com.scoprion.result.BaseResult;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:37
 */
public interface RoleService {

    BaseResult add(SysRole sysRole);

    BaseResult modify(SysRole sysRole);

    BaseResult findByCondition(Integer pageNo, Integer pageSize, String searchKey);

    BaseResult findById(Long id);

    BaseResult deleteById(Long id);

    BaseResult allocationMenu(Long roleId, List<Long> menusId);

    BaseResult bindRole(Long userId, Long roleId);
}
