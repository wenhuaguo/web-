package com.kaishengit.web.user;

import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/16.
 */
@WebServlet("/user/active")
public class ActiveServlet extends SendHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("_");
        //判断token是否存在
        if(token == null){
            //2.如果不存在返回404错误
            resp.sendError(404);
        }else {
            //3.如果存在进行激活验证
            RegService regService = new RegService();
            try {
                regService.active(token);
                //激活成功后请求转发到成功页面
                forward("/user/active_success",req,resp);
            }catch (ServletException ex){
                //激活失败给一个请求转发页面转发到失败界面
                req.setAttribute("message",ex.getMessage());
                forward("/user/active_error",req,resp);
            }
        }

    }
}
