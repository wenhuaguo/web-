package com.kaishengit.web.user;


import com.google.common.collect.Maps;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PostService;
import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2016/12/15.
 */
@WebServlet("/login")
public class LoginServlet extends SendHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断当前是否有用户如果有进行删除
        req.getSession().removeAttribute("curr_user");
        forward("user/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username + "password" + password);
        String ip = req.getRemoteAddr();//获取客户端的ip地址
        RegService regService = new RegService();
        //用json存储数据的格式给客户端进行响应
        Map<String,Object> result = Maps.newHashMap();
        try {

            User user = regService.login(ip,username, password);
            //如果登录成功将该用户放入到session当中，用户验证是否为统一用户
            HttpSession session = req.getSession();
            session.setAttribute("curr_user",user);
            result.put("state","success");
        }catch (ServiceException e){
            result.put("state","error");
            req.setAttribute("message",e.getMessage());
            result.put("message",e.getMessage());
        }
        readJson(result,resp);
    }
}
