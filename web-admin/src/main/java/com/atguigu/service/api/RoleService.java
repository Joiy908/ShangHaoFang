package com.atguigu.service.api;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Joiy908
 * @date 2022/9/13
 */

@Service
public interface RoleService {
    List<Role> findAll();

    Integer insert(Role role);

    Role getById(Long id);

    Integer update(Role role);

    Integer delete(Long id);

    PageInfo<Role> findPage(Map<String, Object> filters);
}
