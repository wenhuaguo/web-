package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Acer on 2016/12/17.
 */
@WebServlet("/foundPassword")
public class FoundPasswordServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/found_password",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        String type = req.getParameter("type");
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        RegService regService = new RegService();
        Map<String,Object> result = Maps.newHashMap();
        result.put("type",type);
        try {
            regService.foundPassword(sessionId, type, value);
            result.put("state","success");
        }catch (ServiceException ex){
            result.put("state","error");
            result.put("message",ex.getMessage());
        }
        readJson(result,resp);
    }
}
