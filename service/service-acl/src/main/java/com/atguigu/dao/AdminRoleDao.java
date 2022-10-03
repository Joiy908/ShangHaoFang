package com.atguigu.dao;


import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleDao extends BaseDao<AdminRole> {
    List<Long> findRoleIdByAdminId(Long adminId);

    void deleteByAdminId(Long adminId);

    void insertRelation(@Param("adminId") Long adminId,@Param("roleId") Long roleId);
}
