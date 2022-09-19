package com.atguigu.dao;

import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;


public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

    List<Role> findPage(Map<String, Object> filters);
}
