package com.atguigu.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpServletRequest request) {
        String nickName = registerVo.getNickName();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if (StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        // 校验验证码
        String codeInSession = (String) request.getSession().getAttribute("CODE");
        if (!code.equals(codeInSession)) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }

        // 查验phone是否已被注册
        UserInfo userInfo = userInfoService.getUserInfoByPhone(phone);
        if (userInfo != null) {
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }

        userInfo = new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setPhone(phone);
        userInfo.setPassword(MD5.encrypt(password));
        userInfo.setStatus(1);
        userInfoService.insert(userInfo);
        return Result.ok();
    }

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        //校验参数
        if(StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(password)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        UserInfo userInfo = userInfoService.getUserInfoByPhone(phone);
        if(null == userInfo) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }

        //校验密码
        if(!MD5.encrypt(password).equals(userInfo.getPassword())) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        request.getSession().setAttribute("USER", userInfo);

        Map<String, Object> map = new HashMap<>();
        map.put("phone", userInfo.getPhone());
        map.put("nickName", userInfo.getNickName());
        return Result.ok(map);
    }
}
