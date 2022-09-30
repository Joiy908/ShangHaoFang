package com.atguigu.service;


import com.atguigu.base.service.BaseService;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

public interface UserFollowService extends BaseService<UserFollow> {
    void follow(Long userId, Long houseId);

    Boolean isFollowed(Long userId, Long houseId);

    PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, Long userId);

    void cancelFollow(Long id);
}
