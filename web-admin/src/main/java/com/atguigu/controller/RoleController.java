package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private final static String PAGE_INDEX = "role/index";
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping
    public String index(ModelMap model) {
        List<Role> list = roleService.findAll();

        model.addAttribute("list", list);
        return PAGE_INDEX;
    }
}