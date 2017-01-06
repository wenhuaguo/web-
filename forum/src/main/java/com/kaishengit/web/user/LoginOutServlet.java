package com.kaishengit.web.user;

import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/17.
 */
@WebServlet("/loginOut")
public class LoginOutServlet extends SendHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.setAttribute("message","您已安全退出");
        forward("user/login",req,resp);
    }
}
