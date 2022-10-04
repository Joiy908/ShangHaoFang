package com.atguigu.service;


import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    List<Map<String, Object>> findPermissionByRoleId(Long roleId);

    void saveRolePermissionRelation(Long roleId, Long[] permissionIds);

    List<Permission> findMenuPermissionByAdminId(Long adminId);

    List<Permission> findAllMenu();
}
