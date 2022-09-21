package com.atguigu.dao;

import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
