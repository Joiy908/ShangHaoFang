package com.atguigu.dao;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.Dict;

import java.util.List;

@Service
public interface DictDao{
    List<Dict> findListByParentId(Long parentId);

    Integer countIsParent(Long id);

    Dict getByDictCode(String dictCode);

    String getNameById(Long id);
}
