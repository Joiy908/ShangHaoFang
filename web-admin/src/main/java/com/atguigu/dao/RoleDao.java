package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

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
}
