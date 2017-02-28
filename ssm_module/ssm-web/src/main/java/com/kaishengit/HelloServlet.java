package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2017/2/28.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = new User();
        user.setName("张三");
        user.setPassword("123");
        userService.saveUser(user);
        resp.sendRedirect("hello.jsp");
    }
}
