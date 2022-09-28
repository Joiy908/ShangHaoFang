package com.atguigu.dao;


import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
