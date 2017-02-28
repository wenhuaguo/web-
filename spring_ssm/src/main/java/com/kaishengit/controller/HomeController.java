package com.kaishengit.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * Created by Acer on 2017/1/13.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(){

        return "login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String login(String userName, String password, RedirectAttributes redirectAttributes){
        //shiro方式登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            return "redirect:/home";
        }catch (AuthenticationException exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
            return "redirect:/";
        }
    }

    //退出
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String longOut(RedirectAttributes redirectAttributes){
        //安全退出
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }



}
