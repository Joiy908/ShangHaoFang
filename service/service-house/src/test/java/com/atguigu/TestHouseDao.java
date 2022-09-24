package com.atguigu;

import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashMap;
import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/22
 */

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-dao.xml"
})
public class TestHouseDao {
    @Autowired
    private HouseDao houseDao;

    @Test
    public void testGetPage() {
        List<House> page = houseDao.findPage(new HashMap<>());
        for (House house : page) {
            System.out.println("house = " + house);
        }
    }

    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Test
    public void TestHBD() {
        List<HouseBroker> hbs = houseBrokerDao.findListByHouseId(1L);
        System.out.println("hbs = " + hbs);
    }

    @Autowired
    private HouseImageDao houseImageDao;

    @Test
    public void testImageDao() {
        final List<HouseImage> list = houseImageDao.findList(1L, 1);
    }
}
