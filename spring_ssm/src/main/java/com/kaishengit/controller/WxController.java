package com.kaishengit.controller;

import com.kaishengit.service.WxService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Acer on 2017/2/24.
 */
@Controller
@RequestMapping("/wx")
public class WxController {

    Logger logger = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private WxService wxService;
    /**
     * 微信初始化
     * @return
     */
    @GetMapping("/init")
    @ResponseBody
    public String init(String msg_signature,String timestamp,String nonce,String echostr){
        logger.info("{}-{}-{}-{}",msg_signature,timestamp,nonce,echostr);

        return wxService.init(msg_signature,timestamp,nonce,echostr);
    }

    @GetMapping("/meeting")
    public String meeting(String code, @RequestHeader("User-Agent") String userAgent, HttpServletResponse response){
        logger.info("user-Agent{}",userAgent);//通过user-agent判断是浏览器还是手机微信端访问
        if(userAgent.contains("wx")){
            logger.info("手机客户端进行了访问");
        }
        String userId = wxService.codeToUserId(code);
        System.out.println("userIdMeeting:" +userId);
        if(userId == null){
            logger.info("未知用户访问");
            return "/wx/403";
        }else {
            logger.info("{}访问来了",userId);
            //给访问用户放入cookie适用一直访问需要用户认证登录的场景
            Cookie cookie = new Cookie("WxUser",userId);//将登录用户放入id
            cookie.setHttpOnly(true);//只允许服务器端读取此cookie
            cookie.setMaxAge(60*60*24*30);//最大存活期限是一个月
            cookie.setPath("/");//访问的路径放入cookie
            response.addCookie(cookie);
            return "wx/meeting";
        }
    }

    @RequestMapping("/abc")
    public String abc(@CookieValue(value = "WxUser",required = false) String userId){
        System.out.println(userId);
        if(StringUtils.isEmpty(userId)){
            logger.info("未知用户访问了abc");
            return "/wx/403";
        }else {
            logger.info("成功到达abc页面");
            return "/wx/abc";
        }
    }
}
