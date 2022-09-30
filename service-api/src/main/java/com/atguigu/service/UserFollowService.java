package com.atguigu.service;


import com.atguigu.base.service.BaseService;
import com.atguigu.entity.UserFollow;

public interface UserFollowService extends BaseService<UserFollow> {
    void follow(Long userId, Long houseId);
}
