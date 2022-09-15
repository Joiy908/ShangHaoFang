package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author Joiy908
 * @date 2022/9/13
 */


public interface RoleDao {
    List<Role> findAll();

    Integer insert(Role role);

    Role getById(Long id);

    Integer update(Role role);

    Integer delete(Long id);

    List<Role> findPage(Map<String, Object> filters);
}
