package com.kaishengit.web.post;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Post;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

/**
 * Created by Acer on 2016/12/24.
 */
@WebServlet("/edit")
public class EditServlet extends SendHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postid");
        PostService postService = new PostService();
        try {
            Post post = postService.findPostById(postId);
            postService.deleteNode(post.getNodeid());
            List<Node> nodeList = postService.readNode();
            req.setAttribute("nodeList",nodeList);
            req.setAttribute("post",post);
            forward("post/editPost",req,resp);
        }catch (ServiceException ex){
            resp.sendError(404,ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postid");
        System.out.println("postId"+postId);
        String title = req.getParameter("title");
        String nodeid = req.getParameter("nodeid");
        String content = req.getParameter("content");
        System.out.println("postId:"+postId+ " title:"+title + "  nodeid:" + nodeid + " content:" +content);
        User user = getSeesion("curr_user",req);
        PostService postService = new PostService();
        JsonResult jsonResult = null;
        try {
            Post post = postService.editPost(postId,user,nodeid,content,title);
            jsonResult = new JsonResult(post);
        }catch (ServiceException ex){
            jsonResult = new JsonResult(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
