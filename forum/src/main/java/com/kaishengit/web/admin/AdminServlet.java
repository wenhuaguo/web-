package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/28.
 */
@WebServlet("/admin/login")
public class AdminServlet extends SendHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果session有值得需要删除
        req.getSession().removeAttribute("admin");
        forward("admin/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminname = req.getParameter("adminname");
        String password = req.getParameter("password");
        String ip = req.getRemoteAddr();//获取用户的ip但是不太靠谱
        AdminService adminService = new AdminService();
        JsonResult jsonResult = new JsonResult();
        try {
            Admin admin = adminService.loginByNameAndPassword(adminname,password,ip);
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin);
            jsonResult.setState("success");
        }catch (ServiceException ex){
            jsonResult.setMessage(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
