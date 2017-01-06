package com.kaishengit.web.post;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Acer on 2016/12/21.
 */
@WebServlet("/reply")
public class ReplyServlet extends SendHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");
        String content = req.getParameter("content");
        Integer userId = getSeesion("curr_user",req).getId();
        PostService postService = new PostService();
        postService.saveReply(postId,content,userId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(JsonResult.SUCCESS);
        readJson(jsonResult,resp);
    }
}
