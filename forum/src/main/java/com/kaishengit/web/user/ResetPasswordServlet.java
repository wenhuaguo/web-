package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Acer on 2016/12/17.
 */
@WebServlet("/foundpassword/newpassword")
public class ResetPasswordServlet extends SendHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        //拿到token首先判断是否为空
        if(StringUtils.isEmpty(token)){
            resp.sendError(404);
        }else {
            //根据token找对应的账号
            try {
                //逻辑关系是根据token  -> username  -> user找到对应的名字根据名字找到对应的用户
                RegService regService = new RegService();
                User user = regService.findUserByToken(token);
                //将token也放入到给客户端的响应中
                req.setAttribute("token",token);
                req.setAttribute("user", user);
                forward("user/resetpassword", req, resp);
            } catch (ServiceException ex) {
                req.setAttribute("message", ex.getMessage());
                //如果无效转向对应的提示页面
                forward("user/reset_error",req,resp);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String id = req.getParameter("id");
        String token = req.getParameter("token");
        Map<String,Object> result = Maps.newHashMap();
        try {
            RegService regService = new RegService();
            regService.changePassword(password,id,token);
            result.put("state","success");
        }catch (ServiceException ex){
            result.put("state","error");
            result.put("message",ex.getMessage());
        }
       readJson(result,resp);
    }
}
