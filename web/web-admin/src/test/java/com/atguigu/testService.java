package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Community;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
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

}
