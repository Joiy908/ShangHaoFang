package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.result.Result;
import com.atguigu.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Reference
    private UserInfoService userInfoService;

    @GetMapping("/sendCode/{phoneNum}")
    public Result sendCode(@PathVariable String phoneNum, HttpServletRequest request) {
        String code = "1111";
        request.getSession().setAttribute("CODE", code);
        // 这里仅供演示使用。
        return Result.ok(code);
    }
}
