package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Joiy908
 * @date 2022/9/28
 */
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-mvc.xml",
})
public class testService {
    @Reference
    private UserInfoService userInfoService;

    @Test
    public void test() {
        userInfoService.getUserInfoByPhone("12245678944");
    }
}
