package com.kaishengit.web.post;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Post;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/27.
 */
@WebServlet("/thank")
public class ThankServelt extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String postid = req.getParameter("postId");
        PostService post = new PostService();
        User user = getSeesion("curr_user",req);
        if(StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(postid)){
            try {
               Post thankPost =  post.thank(type,postid,user.getId());
                JsonResult jsonResult = new JsonResult(thankPost);
                readJson(jsonResult,resp);
            }catch (ServiceException ex){
                resp.sendError(404,ex.getMessage());
            }

        }else {
            resp.sendError(404);
        }
    }
}
