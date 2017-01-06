package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.RegService;
import com.kaishengit.userVo.UserVo;
import com.kaishengit.util.Page;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/29.
 */
@WebServlet("/admin/user")
public class UserManageServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RegService regService = new RegService();
        String p = req.getParameter("p");
        String pageNo = StringUtils.isNumeric(p) ? p :"1";
        Page<UserVo> pageList =  regService.userList(pageNo);
            req.setAttribute("pageList", pageList);
        forward("admin/userManage",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        Integer userState = Integer.valueOf(req.getParameter("userState"));
        //jQuery rel获取
        String content = req.getParameter("content");
        //获取的rel是一个数组用都好分隔
        String[] stateAndId = content.split(",");
        Integer userState = Integer.valueOf(stateAndId[0]);
        String userId = stateAndId[1];
        //用三元表达式进行判断取舍
        userState = userState == 1 ? 2:1;
        RegService regService = new RegService();
        JsonResult jsonResult = new JsonResult();
        try {
            regService.updatUserState(userId,userState);
            jsonResult.setState("success");
        }catch (ServiceException ex){
            jsonResult.setMessage(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
