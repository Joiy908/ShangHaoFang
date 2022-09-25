package com.atguigu;

import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.entity.HouseBroker;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/25
 */
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-dao.xml"
})
public class TestBrokerDao {
    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Test
    @Disabled
    public void testInsert() {
        houseBrokerDao.insert(new HouseBroker(1L, 15L, "test", null));
        List<HouseBroker> brokers = houseBrokerDao.findListByHouseId(1L);
        for (HouseBroker broker : brokers) {
            System.out.println("broker = " + broker);
        }

    }

    @Test
    public void testGet() {
        HouseBroker broker = houseBrokerDao.getById(1);
        System.out.println("broker = " + broker);
    }

    @Test
    public void testRM() {
        houseBrokerDao.delete(7);
    }



}
