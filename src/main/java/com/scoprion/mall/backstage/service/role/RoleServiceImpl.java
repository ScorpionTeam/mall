package com.scoprion.mall.backstage.service.role;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
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

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public BaseResult add(SysRole sysRole) {
        Integer validCount = roleMapper.validByName(sysRole.getName());
        if (validCount > 0) {
            return BaseResult.error("ERROR", "角色名称已经存在");
        }
        roleMapper.add(sysRole);
        return BaseResult.success("添加成功");
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public BaseResult modify(SysRole sysRole) {
        if (sysRole.getId() == null) {
            return BaseResult.parameterError();
        }
        Integer validCount = roleMapper.validByNameAndId(sysRole.getName(), sysRole.getId());
        if (validCount > 0) {
            return BaseResult.error("ERROR", "角色名称已经存在");
        }

        if (CommonEnum.UN_NORMAL.getCode().equals(sysRole.getStatus())) {
            //删除角色,校验角色绑定用户数量
            Integer count = roleMapper.validUserByRoleId(sysRole.getId());
            if (count > 0) {
                return BaseResult.error("ERROR", "角色正在使用中，不可删除");
            }
        }

        Integer result = roleMapper.modify(sysRole);
        if (result <= 0) {
            return BaseResult.error("ERROR", "修改失败");
        }
        return BaseResult.success("修改成功");
    }

    /**
     * 列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
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

    /**
     * 根据id查询角色详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        SysRole sysRole = roleMapper.findById(id);
        if (sysRole == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(sysRole);
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteById(Long id) {
        Integer count = roleMapper.validUserByRoleId(id);
        if (count > 0) {
            return BaseResult.error("ERROR", "角色正在使用中，不可删除");
        }
        roleMapper.deleteById(id);
        return BaseResult.success("delete_success");
    }

    /**
     * @param roleId
     * @param menuIdList
     * @return
     */
    @Override
    public BaseResult bindMenu(Long roleId, List<Long> menuIdList) {
        roleMapper.clearRelation(roleId);
        menuIdList.forEach(menuId -> {
            //查询父节点
            Long parentId = roleMapper.findPidByMenuId(menuId);
            Integer count = roleMapper.queryExistByPid(roleId, parentId);
            if (count > 0) {
                roleMapper.addRoleMenuRelation(roleId, menuId);
            } else {
                roleMapper.insertPid(roleId, parentId);
                roleMapper.addRoleMenuRelation(roleId, menuId);
            }
        });
        return BaseResult.success("绑定菜单成功");
    }

    /**
     * 角色绑定用户
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public BaseResult bindUser(Long userId, Long roleId) {
        Integer count = roleMapper.validRoleRelation(userId);
        if (count > 0) {
            roleMapper.updateRoleRelation(userId, roleId);
        } else {
            roleMapper.addRoleRelation(userId, roleId);
        }
        return BaseResult.success("角色绑定成功");
    }
}
