package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
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

    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);

        setCommonAttr(model);
        return "house/index";
    }

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

    @RequestMapping("/save")
    public String SaveHouse(House house) {
        houseService.insert(house);
        return PAGE_SUCCESS;
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        House house = houseService.getById(id);
        model.addAttribute("house",house);

        setCommonAttr(model);
        return "house/edit";
    }

    @PostMapping(value="/update")
    public String update(House house) {
        houseService.update(house);
        return PAGE_SUCCESS;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return PAGE_SUCCESS;
    }

    private final static String LIST_ACTION = "redirect:/house";

    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status) {
        houseService.publish(id, status);
        return LIST_ACTION;
    }

    /**
     * 详情
     */
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


        return "house/show";
    }

}
