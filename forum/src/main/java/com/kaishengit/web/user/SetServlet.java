package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.RegService;
import com.kaishengit.util.readconfig.ReadConfig;
import com.kaishengit.web.SendHttpServlet;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Created by Acer on 2016/12/18.
 */
@WebServlet("/set")
public class SetServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在向设置页面转的时候将产生的token传递过去
        //1.先导入依赖，然后连接七牛云存储需要一对有效的ak和sk进行签名认证
        //2.进行密匙配置
        Auth auth = Auth.create(ReadConfig.get("qiniu.ak"),ReadConfig.get("qiniu.sk"));
        //产生上传需要验证的token参数设置上传的空间名
        String token = auth.uploadToken(ReadConfig.get("qiniu.bucket"));
        req.setAttribute("token",token);
        forward("user/set",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
        if("email".equals(action)){

            changeEmail(req,resp);
        }else if("password".equals(action)){
            changePassword(req,resp);
        }else if("avatar".equals(action)){
            changeAvatar(req,resp);
        }

    }
    //用于处理修改用户头像
    private void changeAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String avatar = req.getParameter("key");
        RegService regService = new RegService();
            User user = getSeesion("curr_user",req);
            regService.changeAvatar(user,avatar);
            JsonResult result = new JsonResult();
            result.setState(JsonResult.SUCCESS);
            readJson(result,resp);


    }

    //用于处理修改邮箱的代码
    private void changeEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");

        User user = getSeesion("curr_user",req);//获取当前登录用户在session中存储的对象
        System.out.println(user);
        RegService regService = new RegService();
        regService.changeEmail(email,user);
            JsonResult result = new JsonResult();
            result.setState(JsonResult.SUCCESS);
            readJson(result,resp);
    }

    //通过方法将内容封装而不是全部堆积在判断代码块里面
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");
        System.out.println("原密码：" + oldpassword);
        System.out.println("新密码：" + newpassword);

        User user = getSeesion("curr_user",req);//通过创建的方法获取在session中存储的对象
        RegService regService = new RegService();
        try {
            regService.resetPassword(user,oldpassword,newpassword);
            JsonResult json = new JsonResult();
            json.setState(JsonResult.SUCCESS);
            readJson(json,resp);
        }catch (ServiceException ex){
            JsonResult jsonResult = new JsonResult(ex.getMessage());
            readJson(jsonResult,resp);
        }

    }
}
