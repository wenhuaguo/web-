package com.kaishengit.web;



import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Acer on 2016/12/9.
 */
public class SendHttpServlet extends HttpServlet {
    /**
     * 给客户端响应一个json数据
     * @param object
     * @param response
     * @throws IOException
     */
    public void readJson(Object object, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String json = new Gson().toJson(object);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
    /**
     * 请求转发方法
     *
     */
    public void forward(String path, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
       //不进行抓取让它抛异常
        req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp").forward(req,resp);
    }
    /**
     * 给客户端一个字符串响应
     */
    public void readText(String str,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
    }
    /**
     * 获取传递过来的session里面的对象
     */
    public User getSeesion(String key,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(key);
        if(user == null){
            return null;
        }else {
            return user;
        }
    }
}
