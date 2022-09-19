package com.atguigu.service;

import com.atguigu.base.service.BaseService;
import com.atguigu.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends BaseService<Role> {
    List<Role> findAll();
}
