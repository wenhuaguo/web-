package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Acer on 2017/1/10.
 * 基于注解的逻辑控制器表示该类是逻辑控制器类
 * 可以将整块业务处理逻辑放到一个逻辑控制器里面
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(){
        System.out.println("hello spring mvc");
        return "home";
    }
}
