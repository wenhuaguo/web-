package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Post;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PostService;
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
@WebServlet("/admin/post")
public class PostManageServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        //判断p是否为一个数字
        Integer pageNo = StringUtils.isNumeric(p)? Integer.valueOf(p):1;
        String nodeid = null;
        PostService postService = new PostService();
        Page<Post> page = postService.findPostByPageNo(pageNo,nodeid);
        req.setAttribute("page",page);
        forward("admin/postManage",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");
        System.out.println("postId:"+postId);
        PostService postService = new PostService();
        JsonResult jsonResult = new JsonResult();
        try {
            postService.deletePost(postId);
            jsonResult.setState("success");
        }catch (ServiceException ex){
            jsonResult.setMessage(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
