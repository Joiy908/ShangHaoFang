package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/22
 */

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-mvc.xml",
})
public class testService {
    @Reference
    private DictService dictService;

    @Test
    public void testD() {

        String name = dictService.getNameById(1L);
        System.out.println("name = " + name);
    }

    @Reference
    private CommunityService communityService;

    @Test
    public void testGetAll() {
        List<Community> all = communityService.findAll();
        for (Community community : all) {
            System.out.println("community = " + community);
        }
    }

    @Reference
    private HouseService houseService;

    @Test
    public void testHouseSer() {
        final House byId = houseService.getById(1);
        System.out.println("byId = " + byId);
    }

    @Reference
    private HouseBrokerService houseBrokerService;

    @Test
    public void testHBS() {
        List<HouseBroker> hbs = houseBrokerService.findHouseBrokersByHouseId(2L);
        System.out.println("hbs = " + hbs);
    }

    @Reference
    private HouseImageService houseImageService;

    @Test
    public void testHouseImageService() {
        final List<HouseImage> list = houseImageService.findList(1L, 1);
    }

    @Reference
    private HouseUserService houseUserService;

    @Test
    public void testHouseUser() {
        final List<HouseUser> listByHouseId = houseUserService.findListByHouseId(1L);
    }

}
