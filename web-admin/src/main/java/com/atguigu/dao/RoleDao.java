package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/13
 */


public interface RoleDao {
    List<Role> findAll();
}