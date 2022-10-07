package com.atguigu.service;


import com.atguigu.base.service.BaseService;
import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {
    List<Map<String, Object>> findPermissionByRoleId(Long roleId);

    void saveRolePermissionRelation(Long roleId, Long[] permissionIds);

    List<Permission> findMenuPermissionByAdminId(Long adminId);

    List<Permission> findAllMenu();

    List<String> findCodeListByAdminId(Long adminId);
}
