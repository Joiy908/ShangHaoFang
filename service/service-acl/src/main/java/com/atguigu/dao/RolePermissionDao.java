package com.atguigu.dao;

import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission> {

    void deleteByRoleId(Long roleId);

    List<Long> findPermissionIdListByRoleId(Long roleId);

    void insertRelation(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);
}