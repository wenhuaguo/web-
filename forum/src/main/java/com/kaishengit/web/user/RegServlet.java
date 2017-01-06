package com.kaishengit.web.user;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.logging.impl.WeakHashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Acer on 2016/12/15.
 */
@WebServlet("/reg")
public class RegServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/user/reg",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        System.out.println("name:" +username+ "password:"+password+ email+ phone);
        RegService reg = new RegService();

        Map<String,Object> result = new Hashtable();
            //当用户填写所有数据都保存好时，也有可能数据库异常没有保存成功，所有返回信息有两种然后再客户端进行判断
            try {
                reg.save(username, password, email, phone);
                result.put("state", "success");
            }catch (ServiceException ex) {
                result.put("state", "error");
                result.put("message", "注册失败，请稍后再试");
            }
        readJson(result,resp);

    }
}
