package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.AdminService;
import com.atguigu.service.DictService;
import com.atguigu.util.AliyunOSSUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="/dict")
public class DictController extends BaseController {
    @Reference
    private DictService dictService;

    /**
     * 根据编码获取子节点数据列表
     */
    @GetMapping(value = "findListByDictCode/{dictCode}")
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

    /**
     * 根据上级id获取子节点数据列表
     */
    @GetMapping(value = "findListByParentId/{parentId}")
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }


}
