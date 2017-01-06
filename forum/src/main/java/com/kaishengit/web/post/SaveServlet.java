package com.kaishengit.web.post;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Post;
import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/23.
 */
@WebServlet("/postSave")
public class SaveServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String postId = req.getParameter("postId");
        PostService postService = new PostService();
        Integer userId = getSeesion("curr_user",req).getId();
        if(StringUtils.isNotEmpty(type) && StringUtils.isNumeric(postId)) {
            if("save".equals(type)) {
                Post post = postService.savePost(postId, userId);
                JsonResult result = new JsonResult(post);
                readJson(result, resp);
            }else if("delete".equals(type)){
                Post post = postService.deleteFave(postId,userId);
                JsonResult result = new JsonResult(post);
                readJson(result,resp);
            }
        }else {
            resp.sendError(404);
        }
    }
}
