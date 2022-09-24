package com.atguigu.dao;


import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.HouseImage;

import java.util.List;

public interface HouseImageDao extends BaseDao<HouseImage> {
    List<HouseImage> findList(Long houseId, Integer type);
}
