package com.atguigu.service;


import java.util.List;
import java.util.Map;

public interface PermissionService {
    List<Map<String, Object>> findPermissionByRoleId(Long roleId);

    void saveRolePermissionRelation(Long roleId, Long[] permissionIds);
}
