package com.base.system.service;

import com.base.common.entity.system.SysUser;

import java.util.Set;

/**
 * 权限信息 服务层
 *
 * @author swq
 */
public interface ISysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     *
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user);
}
