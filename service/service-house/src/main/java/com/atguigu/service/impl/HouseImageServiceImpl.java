package com.atguigu.service.impl;


import com.atguigu.base.dao.BaseDao;
import com.atguigu.base.service.BaseServiceImp;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.HouseImageService;
import com.atguigu.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HouseImageServiceImpl extends BaseServiceImp<HouseImage> implements HouseImageService {
    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }

    @Override
    public List<HouseImage> findList(Long houseId, Integer type) {
        return houseImageDao.findList(houseId, type);
    }
}
