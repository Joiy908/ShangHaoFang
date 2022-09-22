package com.atguigu;

import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
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
public class TestHouse {
    @Autowired
    private HouseDao houseDao;

    @Test
    public void testGetPage() {
        List<House> page = houseDao.findPage(new HashMap<>());
        for (House house : page) {
            System.out.println("house = " + house);
        }
    }
}
