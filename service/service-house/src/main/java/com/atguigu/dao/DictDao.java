package com.atguigu.dao;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.Dict;

import java.util.List;

@Service
public interface DictDao extends BaseDao<Dict> {
    List<Dict> findListByParentId(Long parentId);

    Integer countIsParent(Long id);
}
