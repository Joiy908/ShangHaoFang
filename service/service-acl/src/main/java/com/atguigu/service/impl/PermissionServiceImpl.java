package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.dao.BaseDao;
import com.atguigu.base.service.BaseServiceImpl;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    protected BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }

    @Override
    public List<Map<String,Object>> findPermissionByRoleId(Long roleId) {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();

        //获取角色已分配的权限数据
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdListByRoleId(roleId);

        //构建ztree数据
        //参考文档：http://www.treejs.cn/v3/demo.php#_201
        // { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        List<Map<String,Object>> zNodes = new ArrayList<>();
        for(Permission permission : permissionList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", permission.getId());
            map.put("pId", permission.getParentId());
            map.put("name", permission.getName());
            if(permissionIdList.contains(permission.getId())) {
                map.put("checked", true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

}