package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.controller.BaseController;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.AliyunOSSUtil;
import com.atguigu.util.QiniuUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping(value="/houseImage")
public class HouseImageController extends BaseController {
    @Reference
    private HouseImageService houseImageService;

    @GetMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(ModelMap model,
                             @PathVariable Long houseId, @PathVariable Long type
    ) {
        model.addAttribute("houseId", houseId);
        model.addAttribute("type", type);
        return "house/upload";
    }

    @PreAuthorize("hasAuthority('house.editImage')")
    @PostMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId,
                         @PathVariable Integer type,
                         @RequestParam(value = "file") MultipartFile[] files)
            throws Exception {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                String newFileName = UUID.randomUUID() + file.getOriginalFilename();
                AliyunOSSUtil.uploadBytes(file.getBytes(), newFileName);

                String url = "https://joiy908.oss-cn-beijing.aliyuncs.com/shangHaoFang/"+newFileName;
                // update database
                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setType(type);
                houseImage.setImageName(file.getOriginalFilename());
                houseImage.setImageUrl(url);
                houseImageService.insert(houseImage);
            }
        }
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('house.editImage')")
    @GetMapping("/delete/{houseId}/{imgId}")
    public String delete(ModelMap model,
                         @PathVariable Long houseId,
                         @PathVariable Long imgId) {
        HouseImage houseImage = houseImageService.getById(imgId);
        houseImageService.delete(imgId);
        // get file name
        String imgUrl = houseImage.getImageUrl();
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/')+1);
        // delete from OSS
        AliyunOSSUtil.deleteFile(fileName);
        return "redirect:/house/" + houseId;
    }
}
