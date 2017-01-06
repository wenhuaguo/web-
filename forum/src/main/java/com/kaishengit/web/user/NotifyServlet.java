package com.kaishengit.web.user;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.Post;
import com.kaishengit.entity.User;
import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/26.
 */
@WebServlet("/notify")
public class NotifyServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在进行跳转到显示页面前将数据读取出来
        RegService regService = new RegService();
        User user = getSeesion("curr_user",req);
        List<Notify> notifyList = regService.findAllNotifyList(user);
        req.setAttribute("notifyList",notifyList);
        forward("user/notify",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegService regService = new RegService();
        //获取当前用户根据当前用户判断发的帖子是否有通知的消息
        User user = getSeesion("curr_user",req);
        List<Notify> notifyList = regService.findAllNotifyList(user);
        System.out.println(notifyList);
        //使用Google的匿名局部内部类进行过滤（按照过滤条件）
        List<Notify> unreadlist = Lists.newArrayList(Collections2.filter(notifyList, new Predicate<Notify>() {
            @Override
            public boolean apply(Notify notify) {
                //将状态为未读的进行返回到新的集合当中去
                return notify.getState() == 0;
            }
        }));
        //给服务端返回数据
        JsonResult jsonResult = new JsonResult(unreadlist.size());
        readJson(jsonResult,resp);
    }
}
