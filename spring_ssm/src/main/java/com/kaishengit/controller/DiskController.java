package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Acer on 2017/2/21.
 */
@Controller
@RequestMapping("/pan")
public class DiskController {

    @Autowired
    private DiskService diskService;
    //公司网盘
    @GetMapping("/list")
    public String networkDisk(@RequestParam(required = false,defaultValue = "0") Integer path, Model model){
        System.out.println(path);
        //上面的请求参数注解的意思的是这个参数不是必须的，默认值为0
        //在这里是根据fid（也就是存储文件和目录是树结构关系表示）查找对应级别的文件或者目录如果
        List<Disk> diskList = diskService.findDiskByFid(path);
        System.out.println(diskList);
        model.addAttribute("diskList",diskList);
        //因为担心从客户端获得fid不靠谱，所有就在给客户端返回的信息里面添加了一个fid的返回信息，也可以从客户端获取fid
        model.addAttribute("fid",path);
        return "setting/disk/networkDisk";
    }

    //新建文件夹
    @PostMapping("/folder/new")
    @ResponseBody
    public AjaxResult folderNew(Disk disk) {
        System.out.println(disk);
        //因为有两个参数可以用disk进行接收
        diskService.newFolder(disk);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

    //文件上传
    @PostMapping("/file/upload")
    @ResponseBody
    public AjaxResult fileUpload(MultipartFile file,Integer fid){
        try {
            diskService.fileUpload(file,fid);
            return  new AjaxResult(AjaxResult.SUCCESS);
        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }
    }

    //文件的下载
    @GetMapping("/downLoad")
    public ResponseEntity<InputStreamResource> downLoad(Integer file) throws FileNotFoundException {
        //获得一个输入流
        Disk disk = diskService.findDiskById(file);
        InputStream inputStream = diskService.downLoad(file);
        HttpHeaders httpHeaders = new HttpHeaders();
        //告诉将流转换为二进制流
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment",disk.getSourceName(), Charset.forName("UTF-8"));
        return new ResponseEntity<>(new InputStreamResource(inputStream),httpHeaders, HttpStatus.OK);
    }

    /**
     *文件和文件夹的删除
     * 会用到递归
     * @param id
     * @return
     */
    @GetMapping("/del")
    @ResponseBody
    public AjaxResult del(Integer id){
        diskService.delFileOrFolder(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }
}
