package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.DictService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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

}
