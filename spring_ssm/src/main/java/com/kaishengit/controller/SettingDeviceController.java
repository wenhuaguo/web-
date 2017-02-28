package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/18.
 * 系统设置中的设备管理控制器
 * 这属于逻辑控制器可以集中处理某一模块的所有请求是中央控制器将对应的请求分发给逻辑控制器
 * 逻辑控制器再做相应的处理
 */
@Controller
@RequestMapping("/setting/device")
public class SettingDeviceController {
    @Autowired
    private DeviceService deviceService;
    //不写具体的请求地址表示和类头请求的名称一样一个类中只能有一个这样的注解
    @GetMapping
    public String list(Model model){
        //List<Device> deviceList = deviceService.findAll();
        //model.addAttribute("deviceList",deviceList);
        return "setting/device/list";
    }

    @GetMapping("/new")
    public String add(){
        return "setting/device/new";
    }

    @PostMapping("/new")
    public String add(Device device, RedirectAttributes redirectAttributes){
        deviceService.add(device);
        //保存成功后给客户端提示用重定向属性flash表示一瞬间
        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/setting/device/deviceInventory";
    }
    @PostMapping("/load")
    @ResponseBody//在方法头加@ResponseBody注解表示返回的不是视图名称而是数据，如果不加表示返回的是视图名字
    public Map<String,Object> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        System.out.println("star:"+ start);
        String length = request.getParameter("length");
        System.out.println("length:" + length);
        String orderIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
        String deviceName = request.getParameter("deviceName");
        System.out.println("orderType:" + orderType);
        System.out.println("orderColumn:" + orderColumn);
        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("orderType",orderType);
        searchParam.put("orderColumn",orderColumn);
        searchParam.put("deviceName",deviceName);

        List<Device> deviceList = deviceService.findDeviceBySearch(searchParam);
        Long count = deviceService.count();
        //搜索过滤后的数量
        Long filteredCount = deviceService.countBySearchParam(searchParam);
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("draw",draw);//记录请求的次数
        resultMap.put("data",deviceList);
        resultMap.put("recordsTotal",count);//总记录数
        resultMap.put("recordsFiltered",filteredCount);//过滤后的总记录数
        System.out.println("filtered:" + filteredCount);
        return resultMap;
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public String del(@PathVariable Integer id){
        deviceService.del(id);
        return "success";
    }


}
