package com.atguigu.service;


import com.atguigu.base.service.BaseService;
import com.atguigu.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {

    List<HouseBroker> findHouseBrokersByHouseId(Long houseId);
}
