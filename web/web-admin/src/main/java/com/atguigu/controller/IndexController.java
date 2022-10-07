package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";
    private final static String PAGE_LOGIN = "frame/login";

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;
    /**
     * 框架首页
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Admin admin = adminService.getByUsername(user.getUsername());
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("permissionList",permissionList);
        return PAGE_INDEX;
    }

    @GetMapping("/login")
    public String login() {
        return PAGE_LOGIN;
    }

    /**
     * 框架主页
     */
    @GetMapping("/main")
    public String main() {

        return PAGE_MAIN;
    }

    private final static String PAGE_NO_AUTH = "frame/accessDeny";
    @GetMapping("/accessDeny")
    public String noAuth() {
        return PAGE_NO_AUTH;
    }
}