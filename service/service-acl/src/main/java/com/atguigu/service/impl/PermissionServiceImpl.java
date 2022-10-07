package com.atguigu.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
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

    @Override
    public void saveRolePermissionRelation(Long roleId, Long[] permissionIds) {
        rolePermissionDao.deleteByRoleId(roleId);

        for(Long permissionId : permissionIds) {
            if(permissionId == null) continue;
            rolePermissionDao.insertRelation(roleId, permissionId);
        }
    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = null;
        //admin账号id为：1
        if(adminId == 1) {
            //如果是超级管理员，获取所有菜单
            permissionList = permissionDao.findAll();
        } else {
            permissionList = permissionDao.findListByAdminId(adminId);
        }
        //把权限数据构建成树形结构数据
        return build(permissionList);
    }

    @Override
    public List<Permission> findAllMenu() {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();
        if(CollectionUtils.isEmpty(permissionList)) return null;

        //构建树形数据,总共三级
        //把权限数据构建成树形结构数据
        return build(permissionList);
    }

    @Override
    public List<String> findCodeListByAdminId(Long adminId) {
        //超级管理员admin账号id为：1
        if(adminId == 1) {
            return permissionDao.findAllCodeList();
        }
        return permissionDao.findCodeListByAdminId(adminId);
    }

    /* helper methods */
    /**
     * 使用递归方法建菜单
     */
    public static List<Permission> build(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if (treeNode.getParentId() == 0) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     */
    public static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());

        for (Permission it : treeNodes) {
            if(treeNode.getId().longValue() == it.getParentId().longValue()) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}