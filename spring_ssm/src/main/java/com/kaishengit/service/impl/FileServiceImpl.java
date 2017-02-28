package com.kaishengit.service.impl;

import com.kaishengit.service.FileService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.UUID;

/**
 * Created by Acer on 2017/2/17.
 */
@Service
public class FileServiceImpl implements FileService {

    //日志文件
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    //读取配置文件
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public String upload(String originalFilename, String contentType, InputStream inputStream) {
        String newFileName;
        if(originalFilename.lastIndexOf(".") != -1) {
            newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        }else {
            newFileName = UUID.randomUUID().toString();
        }
        File file = new File(new File(uploadPath),newFileName);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return newFileName;
        } catch (IOException e) {
            logger.error("文件上传异常",e);
            throw new RuntimeException("文件上传异常",e);
        }
    }
}
