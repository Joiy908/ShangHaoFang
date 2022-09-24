package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {
    @Reference
    private AdminService adminService;


    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_EDIT = "houseBroker/edit";
    private final static String PAGE_SUCCESS = "common/successPage";


    /**
     * 进入新增
     */
    @GetMapping("/create")
    public String create(
            ModelMap model, @RequestParam("houseId") Long houseId) {
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList",adminList);
        model.addAttribute("houseId",houseId);
        return PAGE_CREATE;
    }
}
