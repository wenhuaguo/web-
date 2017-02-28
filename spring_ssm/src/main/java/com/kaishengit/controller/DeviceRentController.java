package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.CompanyContract;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceDoc;
import com.kaishengit.pojo.RentDeviceDetail;
import com.kaishengit.service.DeviceService;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


/**
 * Created by Acer on 2017/1/30.
 */
@Controller
@Configuration
@ImportResource("classpath:applicationContext.xml")
@RequestMapping("/business")
public class DeviceRentController {
    @Autowired
    DeviceService deviceService;

    //读取配置文件
    @Value("${qiniu.ak}")
    private String ak;
    @Value("${qiniu.sk}")
    private String sk;
    @Value("${qiniu.bucket}")
    private String bucket;
    //设备租赁新增
    @GetMapping("/device/addRentDevice")
    public String addRentDevice(Model model) {
        //向添加新增流水页面跳转时要传递token
        System.out.println(ak);
        Auth auth = Auth.create(ak,sk);
        //产生需要上传验证的token，参数为要上传到空间的名称
        String token = auth.uploadToken(bucket);
        List<Device> deviceList = deviceService.findAll();
        model.addAttribute("deviceList",deviceList);
        model.addAttribute("token",token);
        return "setting/device/deviceRentAdd";
    }

    //设备租赁新增
    @PostMapping("/device/addRentDevice")
    @ResponseBody
    public AjaxResult addRentDevice(@RequestBody DeviceRentDto deviceRentDto){
        //将从客服端获得的数据进行封装为一个对象并加上注解
        try {
            String serialNum = deviceService.addRentDeivce(deviceRentDto);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(serialNum);
            ajaxResult.setStatus(AjaxResult.SUCCESS);
            return ajaxResult;
        }catch (ServiceException ex){
            return new AjaxResult(AjaxResult.ERROR,ex.getMessage());
        }


    }
    @GetMapping("/device/ajax")
    @ResponseBody
    public AjaxResult ajax(Integer id){
        System.out.println("id:"+id);
        //用int类型接收AJAX请求的参数
        Device device = deviceService.findDeviceById(id);
        System.out.println(device);
        if(device == null){
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        }else {
            return new AjaxResult(device);
        }
    }

    //设备租赁合同详情
    @GetMapping("/device/rentDetail/{serialNum:\\d+}")
    public String deviceRentDetail(@PathVariable String serialNum,Model model){
        System.out.println("serialNum"+serialNum);
        //查询合同对象
        CompanyContract companyContract = deviceService.findCompantContractBySerialNum(serialNum);
        System.out.println(companyContract);
        if(companyContract == null){
            throw new NotFoundException();
        }else {
            Integer id = companyContract.getId();
            //查询设备列表
            List<RentDeviceDetail> rentDeviceDetail =  deviceService.findDetailByRentId(id);
            //查询文件列表
            List<DeviceDoc> deviceDoc = deviceService.findFileListByRentId(id);
            System.out.println(rentDeviceDetail);
            System.out.println(deviceDoc);
            model.addAttribute("companyContract",companyContract);
            model.addAttribute("fileList",deviceDoc);
            model.addAttribute("deviceList",rentDeviceDetail);
            return "/setting/device/rentDeviceDetail";
        }
    }

    //打包zip下载用原始的文件下载方法进行打包下载
    @GetMapping("/device/rent/zip")
    @ResponseBody
    public void zipFileDownLoad(Integer companyId,HttpServletResponse response) throws IOException {
        //根据合同id查找对应的合同
        CompanyContract companyContract = deviceService.finCompanyContractById(companyId);
        if (companyContract == null){
            throw new NotFoundException();
        }else {
            //将文件下载标志位二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载名称
            String fileName = companyContract.getRentCompany() + ".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            //设置响应头
            response.setHeader("Content-Disposition","attachment;filename=\""+ fileName+"\"");
            //响应输出流
            OutputStream outputStream = response.getOutputStream();
            //创建zipOutputStream对象
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            deviceService.downLoadZipFile(companyContract,zipOutputStream);
        }
    }


    //spring自带的响应流进行下载
    @GetMapping("/device/file/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> fileDownload(Integer id) throws IOException {
        //1.首先先获得输入流
        InputStream inputStream = deviceService.downLoadFile(id);
        //2.判断输入流是否存在
        if(inputStream == null){
            throw new NotFoundException();
        }else {
            //3.根据id求出对应的文件对象
            DeviceDoc deviceDoc = deviceService.findDeviceDocById(id);
            //4.获得对应的原始文件名称
            String fileName = deviceDoc.getSourceFileName();
            //5.创建一个响应头对象
            HttpHeaders httpHeaders = new HttpHeaders();
            //6.根据响应头对象设置对应的二进制文件流和乱码问题的解决
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //7.设置内容位置的格式attachment附件
            httpHeaders.setContentDispositionFormData("attachment",fileName,Charset.forName("UTF-8"));
            //返回响应实体泛型参数为输入流源，响应头对应的映射，以及状态码
            //因为是jdk8所以可以省略掉中间泛型需要的类型
            return new ResponseEntity<>(new InputStreamResource(inputStream),httpHeaders,HttpStatus.OK);
        }
    }




    //设备租赁详情上传文件的下载单个下载
    //因为是文件下载所有不需要返回任何东西，返回为空
    /*
    @GetMapping("/device/file/download")
    public void fileDownload(Integer id, HttpServletResponse response) throws IOException {
        //获得文件的输入流
        InputStream inputStream = deviceService.downLoadFile(id);
        if(inputStream == null){
            throw new NotFoundException();
        }else {
            //根据id查询出来对应的文件上传对象不同判断文件是否存在因为输入流已经存在所有文件对象也就是存在
            DeviceDoc deviceDoc = deviceService.findDeviceDocById(id);
            //告诉浏览器不要打开文件而是下载文件，将文件标记为二进制文件
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = deviceDoc.getSourceFileName();
            //解决文件名有中文乱码的问题
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            //设置相应头
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName + "\"");
            //响应输出流
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }
    */
    //设备库存
    @RequestMapping(value = "/device/inventory",method = RequestMethod.GET)
    public String deviceInventory(){
        return "setting/device/deviceInventory";
    }

    //设备库存表格显示数据
    //设备租赁流水
    @GetMapping("/device/assembly")
    public String deviceRentAssembly(){
        return "setting/device/deviceRentAssembly";
    }

    //设备租赁AJAX请求dataTables请求
    @PostMapping("/device/assembly/load")
    @ResponseBody
    public DataTablesResult assemblyLoad(HttpServletRequest request){
        //参数为获得请求
        String draw = request.getParameter("draw");//记录请求的次数
        //传过来的参数start和length
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        //String orderIndex = request.getParameter("order[0][column]");//第几列参与排序
        //String orderType = request.getParameter("order[0][dir]");//已怎样的方式进行排序
        //String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
        //将自定义搜索几个参数传进来
        String serialNum = request.getParameter("serialNum");
        String rentCompany = request.getParameter("rentCompany");
        String state = request.getParameter("state");
        String rentTime = request.getParameter("rentTime");
        String backTime = request.getParameter("backTime");
        System.out.println(serialNum + rentCompany + state + rentTime + backTime);
        //将参数封装到一个Map集合里面
        Map<String,String> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);
        queryParam.put("rentCompany",rentCompany);
        List<CompanyContract> companyContractList = deviceService.finaAllDeivceContractByQueryParam(queryParam);
        Long count = deviceService.count();//统计设备合同的数量
        //过滤后的数量
        Long filteredCount = deviceService.filteredCount(queryParam);
        //dataTables需要返回几个数据一个是记录第几次请求的次数一个是总记录数和过滤数如果没有其它参数那么原则上他们之间是相等得
        return new DataTablesResult(draw,companyContractList,count,filteredCount);


    }

    //更改设备租赁合同状态
    @PostMapping("/device/contract/state")
    @ResponseBody
    public AjaxResult changeDeviceRentContractState(Integer id){
        deviceService.changeState(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }
    /*
    @PostMapping("/deviceInventory/load")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        //记录第几次请求
        String draw = request.getParameter("draw");
        //获取从哪开始以及长度是多少
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String orderIndex = request.getParameter("order[0][column]");
        String oderType = request.getParameter("order[0][dir]");
        String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
        String deviceName = request.getParameter("deviceName");
        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("oderColumn",orderColumn);
        searchParam.put("orderType",oderType);
        searchParam.put("deviceName",deviceName);
        List<Device> deviceList = deviceService.findDeviceBySearch(searchParam);
        for(Device device:deviceList){
            System.out.println(device.getCreateTime());
        }
        //过滤前的数量
        Long count = deviceService.count();
        //过滤后的数量
        Long filteredCount = deviceService.countBySearchParam(searchParam);
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("draw",draw);
        resultMap.put("recordsTotals",count);
        resultMap.put("recordsFiltered",filteredCount);
        resultMap.put("data",deviceList);
        return resultMap;
    }

*/











}
