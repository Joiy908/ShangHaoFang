package com.atguigu.service;

import com.atguigu.base.service.BaseService;
import com.atguigu.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService extends BaseService<Role> {
    List<Role> findAll();

    /**
     * 根据用户获取角色数据
     */
    Map<String, Object> findRoleByAdminId(Long adminId);
}
