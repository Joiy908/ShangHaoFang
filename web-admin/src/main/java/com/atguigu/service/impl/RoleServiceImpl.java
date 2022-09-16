package com.atguigu.service.impl;

import com.atguigu.base.dao.BaseDao;
import com.atguigu.base.service.BaseServiceImp;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl extends BaseServiceImp<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    protected BaseDao<Role> getEntityDao() {
        return this.roleDao;
    }
}
