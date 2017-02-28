package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.LabourRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.*;
import com.kaishengit.service.FileService;
import com.kaishengit.service.LabourService;
import org.apache.commons.io.IOUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Acer on 2017/2/18.
 * 劳务外包业务
 */
@Controller
@RequestMapping("/labour")
public class LabourEpibolyController {
    @Autowired
    private LabourService labourService;

    @Autowired
    private FileService fileService;
    //新增劳务流水
    @GetMapping("/addAssembly")
    public String labourAddAssembly(Model model){
        List<LabourType> labourList = labourService.findAll();
        model.addAttribute("labourList",labourList);
        return "setting/labour/addAssembly";
    }

    //根据id查找对象的工种信息
    @GetMapping("/dispatch/labour.json")
    @ResponseBody
    public AjaxResult labourDispatch(Integer id){
        LabourType labourType = labourService.findLabourById(id);
        //判断存在不存在并通过AjaxResult给客户端返回信息
        if(labourType == null){
            return new AjaxResult(AjaxResult.ERROR,"该工种信息不存在");
        }else {
            return new AjaxResult(labourType);
        }

    }

    @PostMapping("/file/upload")
    @ResponseBody
    public AjaxResult fileUpload(@RequestBody MultipartFile file){
        //参数必须和上传的file控件的名称一致
        //对于文件上传spring-Servlet.xml需要配置上传文件解析器,并用MultipartFile来接收上传的文件
        //新的文件名称需要返回
        try {
            String newFileName = fileService.upload(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
            System.out.println(newFileName);
            Map<String,String> map = Maps.newHashMap();
            map.put("sourceFile",file.getOriginalFilename());
            map.put("newFile",newFileName);
            return new AjaxResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }

    }

    //添加劳务租赁合同
    @PostMapping("/ajax.json")
    @ResponseBody
    public AjaxResult addLabourContract(@RequestBody LabourRentDto labourRent){
        //用Dto封装从客户端过来的数据
        //要思考服务端怎样接收客户端的json数据以及客户端怎样给服务端传递数据数据的形式是什么样的
        //返回一个流水号
        try {
            String serialNum = labourService.addLabourRentContract(labourRent);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setStatus("success");
            ajaxResult.setData(serialNum);
            return ajaxResult;
        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }

    }

    //转到劳务租赁合同详情页面
    @GetMapping("/contract/detail/{id:\\d+}")
    public String labourContractDetail(@PathVariable String id,Model model){
        System.out.println(id);
        //根据id可以查询出来劳务租赁合同详情，以及劳务租赁列表和文件上传列表
        //根据流水号查询公司劳务租赁合同详情
        LabourContract companyContract = labourService.findCompanyLabourContract(id);
        if (companyContract != null){
            //根据rentId查询对应的劳务租赁列表
            List<LabourDetail> labourDetailList = labourService.findLabourDetailByRentId(companyContract.getId());
            List<DeviceDoc> labourFileUpload = labourService.findFileUploadListByRentId(companyContract.getId());
            model.addAttribute("companyContract",companyContract);
            model.addAttribute("labourDetailList",labourDetailList);
            model.addAttribute("labourFileUpload",labourFileUpload);
            //根据RentId查询对应的文件上传列表
        }else {
            throw new NotFoundException();
        }
        return "/setting/labour/labourRentContractDetail";
    }
    //劳务业务流水
    @GetMapping("/businessAssembly")
    public String businessAssembly(){
        return "setting/labour/businessAssembly";
    }

    //劳务流水显示
    @GetMapping("list/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request){
        //dataTables规定服务端给客户端返回的数据必须包括这几个参数的数据
        String draw = request.getParameter("draw");
        //请求的开始和长度
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        //将查询参数进行封装为一个Map集合
        Map<String,String> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);
        List<LabourContract> labourContractList = labourService.findLabourContractByQueryParam(queryParam);
        //查询总条数
        Long count = labourService.count();
        //查询过滤后的数据因为没有其他参数所有过滤后的条数和总条数一直
        return new DataTablesResult(draw,labourContractList,count,count);
    }


    //合同状态的修改
    @GetMapping("/change/state")
    @ResponseBody
    public AjaxResult changeState(Integer id){
        labourService.changeContractStateById(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

    //利用spring内置的方法进行下载文件
    @GetMapping("/download/file")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        InputStream inputStream = labourService.downloadFile(id);
        if(inputStream == null){
            throw new NotFoundException();
        }else {
            DeviceDoc deviceDoc = labourService.findDeviceDoById(id);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment",deviceDoc.getSourceFileName(), Charset.forName("UTF-8"));
            return new ResponseEntity<>(new InputStreamResource(inputStream),httpHeaders, HttpStatus.OK);//有三个参数一个是输入流，一个是响应头和状态码状态码要是上面这个形式
        }
    }



    //文件的下载普通的文件下载
    /*
    @GetMapping("/download/file")
    @ResponseBody
    public void downloadFile(Integer id,HttpServletResponse response) throws IOException {
        InputStream inputStream = labourService.downloadFile(id);
        //判断输入流是否存在
        if(inputStream == null){
            throw new NotFoundException();
        }else {
            //根据id查询一次文件对象
            DeviceDoc deviceDoc = labourService.findDeviceDoById(id);
            String fileName = deviceDoc.getSourceFileName();
            //解决中文乱码问题
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }
    */
    //合同的下载（打包下载）
    @GetMapping("download/zip")
    @ResponseBody
    public void downloadZip(Integer id, HttpServletResponse response) throws IOException {
        //首先查找出来id对应的合同详细
        LabourContract labourContract = labourService.findLabourContractById(id);
        //并判断该合同是否存在
        if (labourContract == null){
            throw new NotFoundException();
        }else {
            //将文件标志为二进制流
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载名称并解决下载文件中中文乱码问题
            String fileName = labourContract.getRentCompany() + ".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");//解决下载文件中含有中文的中文乱码问题
            //设置响应头
            response.setHeader("Content-Disposition","attachment;filename=\"" + fileName+"\"");
            //响应输出流
            OutputStream outputStream = response.getOutputStream();
            //创建压缩文件包响应输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            //进行打包
            labourService.downloadZip(labourContract,zipOutputStream);

        }


    }}
