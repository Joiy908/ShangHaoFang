package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    @Reference
    private HouseService houseService;

    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private HouseUserService houseUserService;

    private final static String PAGE_SUCCESS = "common/successPage";

    @PreAuthorize("hasAuthority('house.show')")
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);

        setCommonAttr(model);
        return "house/index";
    }

    @PreAuthorize("hasAuthority('house.create')")
    @RequestMapping("/create")
    public String createHouse(ModelMap model) {
        setCommonAttr(model);
        return "house/create";
    }

    /** helper method to avoid duplicated codes */
    private void setCommonAttr(ModelMap model) {
        model.addAttribute("communityList",communityService.findAll());
        model.addAttribute("houseTypeList",dictService.findListByDictCode("houseType"));
        model.addAttribute("floorList",dictService.findListByDictCode("floor"));
        model.addAttribute("buildStructureList",dictService.findListByDictCode("buildStructure"));
        model.addAttribute("directionList",dictService.findListByDictCode("direction"));
        model.addAttribute("decorationList",dictService.findListByDictCode("decoration"));
        model.addAttribute("houseUseList",dictService.findListByDictCode("houseUse"));
    }

    @PreAuthorize("hasAuthority('house.create')")
    @RequestMapping("/save")
    public String SaveHouse(House house) {
        houseService.insert(house);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('house.edit')")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        House house = houseService.getById(id);
        model.addAttribute("house",house);

        setCommonAttr(model);
        return "house/edit";
    }

    @PreAuthorize("hasAuthority('house.edit')")
    @PostMapping(value="/update")
    public String update(House house) {
        houseService.update(house);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('house.delete')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return PAGE_SUCCESS;
    }

    private final static String LIST_ACTION = "redirect:/house";

    @PreAuthorize("hasAuthority('house.publish')")
    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status) {
        houseService.publish(id, status);
        return LIST_ACTION;
    }

    /**
     * ??????
     */
    @PreAuthorize("hasAuthority('house.show')")
    @GetMapping("/{id}")
    public String show(ModelMap model,@PathVariable Long id) {
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        model.addAttribute("house", house);
        model.addAttribute("community", community);

        List<HouseBroker> HBs = houseBrokerService.findHouseBrokersByHouseId(house.getId());
        model.addAttribute("houseBrokerList", HBs);

        List<HouseImage> houseImage1List = houseImageService.findList(house.getId(), 1);
        List<HouseImage> houseImage2List = houseImageService.findList(house.getId(), 2);
        model.addAttribute("houseImage1List", houseImage1List);
        model.addAttribute("houseImage2List", houseImage2List);

        List<HouseUser> houseUserList = houseUserService.findListByHouseId(house.getId());
        model.addAttribute("houseUserList",houseUserList);

        return "house/show";
    }

}
