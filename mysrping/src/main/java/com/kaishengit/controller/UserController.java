package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Account;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Acer on 2017/1/10.
 * 基于注解逻辑控制器层用户处理某一业务模块
 * 在类头写请求注解表示所有想进入该层的请求都必须包括/user
 * url的风格
 */
@Controller
@RequestMapping("/user")
public class UserController {
//    如果注解的参数是一个的话默认是给value赋值
    //注解两个参数的话必须加上value写上方法意味着只可以接口get的请求方法
//    @GetMapping("/add")//只接受get方式的请求但版本必须是>=4.3的版本
//    @RequestMapping(value = "/add",method = RequestMethod.GET)
//    public String add(){
//        return "/user/add";
//    }
    //只接受post方式请求
    //如果是多个参数的话可以对参数进行封装到一个对象中用对象作为参数，如果对象中没有要传递的参数的话也不会报错
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public String add(Account account,String address){
//        System.out.println(account.getUserName()+"," + account.getAge() + ","+ address + "save success");
//    //重定向到home主页可以这样写
//        return "redirect:/home";
//    }
    //URL的风格问题表示请求的地址格式是/user?id=xx
//    @RequestMapping(method = RequestMethod.GET)
//    public String showUser(int id){
//        System.out.println(id);
//        return "redirect:/home";
//    }
    //在URL中参数id数字
//    @RequestMapping(value = "/{id:\\d{3,}}",method = RequestMethod.GET)
//    public String showUser(@PathVariable Integer id){
//        //上面的注解是获取地址栏中id的值并且必须是三位的数字
//        System.out.println("path id:" + id);
//        return "redirect:/home";
//    }
    //地址栏多个参数的问题
//    @RequestMapping(value = "/{id:\\d+}/{types}",method = RequestMethod.GET)
//    public String showUser(@PathVariable Integer id,
//                            @PathVariable(name = "types") String type,
//                           Model model
//    ){
        //变量参数的变量必须和URL地址栏中参数的名字必须相同不同的话加name属性命名
        //多加一个参数目的是向视图层传送数据是键值对形式的数据
    /*
    System.out.println("id:" + id + "types:" + types);
        model.addAttribute("id",id);
//        model.addAttribute("types",types);
        return "/user/show";
    }
    */
    //另一种方式向视图传送数据
//    @RequestMapping(value = "/{id:\\d+}/{types}",method = RequestMethod.GET)
//    public ModelAndView showUser(@PathVariable Integer id,
//                                 @PathVariable(name = "types") String type
//                                 ){
//        System.out.println("id:" +id +"type:" + type);
        //创建一个对象模型层和视图层相关联
      //  ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("user/show");//设置视图层的名字
        //modelAndView.addObject("id",id);//还是键值对的形式
        //modelAndView.addObject("types",type);

        //如果只有一个参数的话可以这样写
//        return new ModelAndView("/user/show","id",id);
//    }
    @RequestMapping(value = "/type",method = RequestMethod.GET)
    public String showUser(int id,String name) throws Exception{
        System.out.println("id:" + id);
        //解决中文乱码问题
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");
        System.out.println("name:" + name);
        return "redirect:/home";
    }

    @GetMapping
    public String showList(Model model){
        //通过model对象向视图层传递数据是json数据格式键值对形式
        Account account = new Account();
        account.setAge(10);
        account.setUserName("李四");
        Account account1 = new Account();
        account1.setAge(11);
        account1.setUserName("王五");
        List<Account> accountList = Arrays.asList(account,account1);
        model.addAttribute("accountList",accountList);
        return "user/show";
    }


    @GetMapping("/add")
    public String add(){
        return "user/add";
    }
    @PostMapping("/add")
    public String add(Account account, RedirectAttributes attributes){
        //springmvc对应post请求传递过来的数据，可以对数据进行封装避免多个参数的过程的问题
        System.out.println("userName:"+ account.getUserName() + "age:" + account.getAge());
        //重定向到另一个网页时可以给地址栏赋值也可以通过springmvc自带的重定向属性给客户端传递提示信息
        //一闪而逝
        attributes.addFlashAttribute("message","保存成功");
        return "redirect:/user";
    }

    //查看地址栏中的名字是否可用
    //在方法头加@ResponseBody注解表示返回的不是视图的名称而是数据，如果不加表示返回的视图的名称
    //用@produces注解的形式指定响应头
    @GetMapping(value = "/check/{userName}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String checkUserName(@PathVariable String userName){
        //通过@PathVariable注解的形式获得地址栏中变量的数据
        System.out.println("userName:" + userName);
        if("tom".equals(userName)){
            return "不可用";
        }else {
            return "可用";
        }
    }
    @RequestMapping(value = "/acc/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public Account account(@PathVariable Integer id){
        //给客户端响应的数据格式是json数据格式键值对形式
        System.out.println("id:"+id);
        Account account = new Account("李四",123);
        return account;
    }
    @PostMapping("/test")
    public String test(String aa,MultipartFile file) throws IOException {
        System.out.println(file);
        if(!file.isEmpty()){
            //上传控件name属性的值
            System.out.println(file.getName());
            //mime type 类型
            System.out.println(file.getContentType());
            //上传文件的大小（字节）
            System.out.println(file.getSize());
            //上传文件的原始名称
            System.out.println(file.getOriginalFilename());
            //获得上传文件的输入流
            InputStream inputStream = file.getInputStream();
            //进行输出
            FileOutputStream outputStream = new FileOutputStream(new File("D:/", file.getOriginalFilename()));
            //拷贝
            IOUtils.copy(inputStream, outputStream);
        }
        System.out.println(aa);
        return "redirect:/user";
    }
    @RequestMapping("/http")
    public String http(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "user/show";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String saveFile(){
        return "user/upload";
    }
    //对于文件上获取文件数据用MultipartFile类型进行接收
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String saveFile(String aa) throws IOException {
        System.out.println("name:" + aa);
        /*
        if(!file.isEmpty()) {
            System.out.println(file.getName());

            System.out.println(file.getContentType());
            System.out.println(file.getSize());
            System.out.println(file.getOriginalFilename());
            InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(new File("D:/", file.getOriginalFilename()));
            IOUtils.copy(inputStream, outputStream);
        }
        */
        return "redirect:/user";
    }
    @RequestMapping("/u-{id:\\d+}")
    public String showUser(@PathVariable Integer id){
            if(id == 100){
                throw new NotFoundException();
            }
            return "redirect:/user";
    }
}

