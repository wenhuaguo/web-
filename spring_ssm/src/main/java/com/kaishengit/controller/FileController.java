package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Acer on 2017/2/17.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService filService;

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult fileUpload(MultipartFile file){
        //spring 关于文件上传的接收
        try {
            String fileName = filService.upload(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
            Map<String,String> map = Maps.newHashMap();
            map.put("newFileName",fileName);
            map.put("sourceFileName",file.getOriginalFilename());
            return new AjaxResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }
    }
}
