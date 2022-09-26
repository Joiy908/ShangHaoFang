package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.util.AliyunOSSUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")

public class AdminController extends BaseController {
    @Reference
    private AdminService adminService;


    private final static String LIST_ACTION = "redirect:/admin";

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    /**
     * 列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * 进入新增页面
     */
    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     */
    @PostMapping("/save")
    public String save(Admin admin) {
        //设置默认头像
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        adminService.insert(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 进入编辑页面
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     */
    @PostMapping("/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return LIST_ACTION;
    }

    /**
     * 上传用户头像
     */
    @GetMapping("/uploadShow/{id}")
    public String uploadShow(ModelMap model,@PathVariable Long id) {
        model.addAttribute("id", id);
        return "admin/upload";
    }

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable Long id,
                         @RequestParam(value = "file") MultipartFile file,
                         HttpServletRequest request) throws IOException {
        String newFileName =  UUID.randomUUID() + file.getOriginalFilename() ;
        // 上传图片
        AliyunOSSUtil.uploadBytes(file.getBytes(), newFileName);
        String url = "https://joiy908.oss-cn-beijing.aliyuncs.com/shangHaoFang/"+newFileName;

        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl(url);
        adminService.update(admin);
        return PAGE_SUCCESS;
    }
}
