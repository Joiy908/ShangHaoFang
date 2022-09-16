package com.atguigu.dao;

import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author Joiy908
 * @date 2022/9/13
 */


public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

    List<Role> findPage(Map<String, Object> filters);
}
