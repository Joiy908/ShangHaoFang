package com.atguigu.service;

import com.atguigu.base.service.BaseService;
import com.atguigu.entity.Community;

import java.util.List;


public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
