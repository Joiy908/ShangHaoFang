package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {
    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        // put filter to request
        map.put("filters", filters);
        PageInfo<Community> page = communityService.findPage(filters);
        map.put("page", page);
        // by default, render Beijing's list
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);

        return "community/index";
    }


}
