package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {
    @Reference
    private AdminService adminService;

    @Reference
    private HouseBrokerService houseBrokerService;


    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_EDIT = "houseBroker/edit";
    private final static String PAGE_SUCCESS = "common/successPage";


    /**
     * 进入新增
     */
    @PreAuthorize("hasAuthority('house.editBroker')")
    @GetMapping("/create")
    public String create(
            ModelMap model, @RequestParam("houseId") Long houseId) {
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList",adminList);
        model.addAttribute("houseId",houseId);
        return PAGE_CREATE;
    }

    /**
     * only admin can be a broker
     */
    @PreAuthorize("hasAuthority('house.editBroker')")
    @PostMapping("/save")
    public String save(HouseBroker houseBroker) {
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.insert(houseBroker);
        return PAGE_SUCCESS;
    }


    /**
     * edit here, don't change properties of Admin,
     * but to change the broker of house in broker_t
     */
    @PreAuthorize("hasAuthority('house.editBroker')")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        HouseBroker houseBroker = houseBrokerService.getById(id);
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList",adminList);
        model.addAttribute("houseBroker",houseBroker);
        return PAGE_EDIT;
    }

    @PreAuthorize("hasAuthority('house.editBroker')")
    @PostMapping(value="/update")
    public String update(HouseBroker houseBroker) {
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.update(houseBroker);

        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('house.editBroker')")
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id) {
        houseBrokerService.delete(id);
        return LIST_ACTION + houseId;
    }
}
