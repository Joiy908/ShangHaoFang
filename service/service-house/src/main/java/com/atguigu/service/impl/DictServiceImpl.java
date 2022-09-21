package com.atguigu.service.impl;


import com.atguigu.dao.DictDao;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Dict;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = DictService.class)
@Transactional
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public List<Map<String,Object>> findZnodes(Long id) {
        // 返回数据[{ id:2, isParent:true, name:"随意勾选 2"}]
        //根据id获取子节点数据
        //判断该节点是否是父节点
        //全部权限列表
        List<Dict> dictList = dictDao.findListByParentId(id);

        List<Map<String, Object>> ZNodeTree = new ArrayList<>();
        for (Dict dict : dictList) {
            Integer count = dictDao.countIsParent(dict.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("id", dict.getId());
            map.put("isParent", count > 0);
            map.put("name", dict.getName());
            ZNodeTree.add(map);
        }
        return ZNodeTree;
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        Dict parent = dictDao.getByDictCode(dictCode);
        if (parent == null) return null;
        return findListByParentId(parent.getParentId());
    }

    @Override
    public String getNameById(Long id) {
        return dictDao.getNameById(id);
    }
}
