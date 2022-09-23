package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.House;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);

        model.addAttribute("communityList", communityService.findAll());
        model.addAttribute("houseTypeList", dictService.findListByDictCode("houseType"));
        model.addAttribute("floorList",dictService.findListByDictCode("floor"));
        model.addAttribute("buildStructureList",dictService.findListByDictCode("buildStructure"));
        model.addAttribute("directionList",dictService.findListByDictCode("direction"));
        model.addAttribute("decorationList",dictService.findListByDictCode("decoration"));
        model.addAttribute("houseUseList",dictService.findListByDictCode("houseUse"));
        return "house/index";
    }

}
