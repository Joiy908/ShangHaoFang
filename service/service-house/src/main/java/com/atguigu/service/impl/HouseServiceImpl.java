package com.atguigu.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.dao.BaseDao;
import com.atguigu.base.service.BaseServiceImp;
import com.atguigu.dao.DictDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServiceImpl extends BaseServiceImp<House> implements HouseService {
    @Autowired
    private HouseDao houseDao;

    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseDao.update(house);
    }

    @Autowired
    private DictDao dictDao;

    /**
     * 重写该方法, 为了展现朝向、户型、楼层等信息。
     * house_type_id,floor_id,build_structure_id,
     * direction_id,decoration_id,house_use_id
     */
    @Override
    public House getById(Serializable id) {
        House h = houseDao.getById(id);
        if (h == null) return null;
        String hName = dictDao.getNameById(h.getHouseTypeId());
        h.setHouseTypeName(hName);
        String fName = dictDao.getNameById(h.getFloorId());
        h.setFloorName(fName);
        String buildStructure = dictDao.getNameById(h.getBuildStructureId());
        h.setBuildStructureName(buildStructure);
        String directionName = dictDao.getNameById(h.getDirectionId());
        h.setDirectionName(directionName);
        String decorationName = dictDao.getNameById(h.getDecorationId());
        h.setDecorationName(decorationName);
        String houseUse = dictDao.getNameById(h.getHouseUseId());
        h.setHouseUseName(houseUse);
        return h;
    }
}
