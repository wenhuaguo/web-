package com.kaishengit.web.post;


import com.kaishengit.entity.*;


import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/20.
 */
@WebServlet("/showPost")
public class ShowPostServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PostService postService = new PostService();
        //统计某一帖子的点击次数在转向页面的时候点击次数加1
        String postId = req.getParameter("postId");
        //判断当前用户是否收藏该帖子
        User user = getSeesion("curr_user",req);
        //判断当前登录用户是否感谢该贴

        try {

            if(user != null){
                Thank thank = postService.findThankByUserIdAndPostId(user.getId(),postId);
                Save save =  postService.findSaveByUserIdAndPostId(postId,user.getId());
                req.setAttribute("save",save);
                req.setAttribute("thank",thank);
            }
                postService.clickCount(postId);
                Post post = postService.findPostById(postId);
                List<Reply> replyList = postService.findAllReply(postId);
                req.setAttribute("replyList",replyList);
                req.setAttribute("post", post);
                forward("post/showPost", req, resp);

        }catch (ServletException ex) {
            resp.sendError(404,ex.getMessage());
        }
    }
}
