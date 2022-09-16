package com.atguigu.controller;

import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.api.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    private final static String PAGE_INDEX = "role/index";
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Role> pageInfo = roleService.findPage(filters);

        model.addAttribute("page", pageInfo);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    private final static String PAGE_CREATE = "role/create";

    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    private final static String PAGE_SUCCESS = "common/successPage";
    @PostMapping("/save")
    public String save(Role role, HttpServletRequest request) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    private final static String PAGE_EDIT = "role/edit";
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_EDIT;
    }

    @PostMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    private final static String LIST_ACTION = "redirect:/role";
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return LIST_ACTION;
    }

}