package com.scoprion.mall.backstage.service.role;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.RoleMapper;
import com.scoprion.mall.domain.SysRole;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:43
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseResult add(SysRole sysRole) {
        Integer validCount = roleMapper.validByName(sysRole.getName());
        if (validCount > 0) {
            return BaseResult.error("name_exist", "角色名称已经存在");
        }
        roleMapper.add(sysRole);
        return BaseResult.success("添加成功");
    }

    @Override
    public BaseResult modify(SysRole sysRole) {
        Integer validCount = roleMapper.validByNameAndId(sysRole.getName(), sysRole.getId());
        if (validCount > 0) {
            return BaseResult.error("name_exist", "角色名称已经存在");
        }
        Integer result = roleMapper.modify(sysRole);
        if (result <= 0) {
            return BaseResult.error("modify_error", "修改失败");
        }
        return BaseResult.success("修改成功");
    }

    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        PageHelper.startPage(pageNo, pageSize);
        Page<SysRole> pages = roleMapper.findByCondition(searchKey);
        return new PageResult(pages);
    }

    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        SysRole sysRole = roleMapper.findById(id);
        return BaseResult.success(sysRole);
    }

    @Override
    public BaseResult deleteById(Long id) {
        SysRole sysRole = roleMapper.findById(id);
        if (Constant.STATUS_ZERO.equals(sysRole.getStatus())) {
            roleMapper.deleteById(id);
        } else {
            return BaseResult.error("unable_delete", "菜单使用中，不可删除");
        }
        return BaseResult.success("delete_success");
    }

    @Override
    public BaseResult allocationMenu(Long roleId, List<Long> menuIdList) {
        Integer result = roleMapper.clearRelation(roleId);
        if (result <= 0) {
            return BaseResult.error("bind_error", "绑定失败");
        }
        menuIdList.forEach(menuId -> {
            //查询父节点
            Long parentId = roleMapper.queryPidByMenuId(menuId);
            Integer count = roleMapper.queryExistByPid(roleId, parentId);
            if (count > 0) {
                roleMapper.updateRoleMenuRelation(roleId, menuId);
            } else {
                roleMapper.insertPid(roleId, parentId);
                roleMapper.updateRoleMenuRelation(roleId, menuId);
            }
        });
        return BaseResult.success("绑定菜单成功");
    }

    @Override
    public BaseResult bindRole(Long userId, Long roleId) {
        Integer count = roleMapper.validRoleRelation(userId);
        if (count > 0) {
            roleMapper.updateRoleRelation(userId, roleId);
        } else {
            roleMapper.insertRoleRelation(userId, roleId);
        }
        return BaseResult.success("角色绑定成功");
    }
}
