package com.kaishengit.web.admin;

import com.kaishengit.entity.PostReplyCount;
import com.kaishengit.service.PostService;
import com.kaishengit.service.RegService;
import com.kaishengit.util.Page;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/28.
 */
@WebServlet("/admin/home")
public class HomeServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)? Integer.valueOf(p):1;
        PostService postService = new PostService();
        Page<PostReplyCount> postList = postService.findAllPost(pageNo);
        req.setAttribute("postList",postList);
        forward("admin/home",req,resp);
        //forward("admin/home1",req,resp);
    }
}
