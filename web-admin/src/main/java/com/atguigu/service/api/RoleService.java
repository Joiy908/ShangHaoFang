package com.atguigu.service.api;

import com.atguigu.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/13
 */

@Service
public interface RoleService {
    List<Role> findAll();
}
