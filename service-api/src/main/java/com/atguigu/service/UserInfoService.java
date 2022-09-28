package com.atguigu.service;

import com.atguigu.base.service.BaseService;
import com.atguigu.entity.UserInfo;


public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getUserInfoByPhone(String phone);
}
