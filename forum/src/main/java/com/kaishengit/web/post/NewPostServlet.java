package com.kaishengit.web.post;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Post;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PostService;
import com.kaishengit.util.readconfig.ReadConfig;
import com.kaishengit.web.SendHttpServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/20.
 */
@WebServlet("/newPost")
public class NewPostServlet extends SendHttpServlet {
    private PostService postService = new PostService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //在向设置页面转的时候将产生的token传递过去
        //1.先导入依赖，然后连接七牛云存储需要一对有效的ak和sk进行签名认证
        //2.进行密匙配置
        Auth auth = Auth.create(ReadConfig.get("qiniu.ak"),ReadConfig.get("qiniu.sk"));
       // "{\"success\":true,\"file_path\":\"http://ohwnpkfcx.bkt.clouddn.com/${key}\"}"
        //少写一个双影号"，注意转义
        String returnBody ="{\"success\":true,\"file_path\":\""+ReadConfig.get("qiniu.internate")+"${key}\"}";
        StringMap map = new StringMap();
        map.put("returnBody",returnBody);
        //产生上传需要验证的token参数设置上传的空间名
        String token = auth.uploadToken(ReadConfig.get("qiniu.bucket"),null,3600,map);
        req.setAttribute("token",token);
        //在转向发帖内容之前先去数据库将节点读取出来
        List<Node> nodeList = postService.readNode();

        req.setAttribute("nodeList",nodeList);
        forward("post/newpost",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeId = req.getParameter("nodeid");
        User user = getSeesion("curr_user",req);
        PostService postService = new PostService();
        Post post = null;
        JsonResult jsonResult =null;
        try {
            post = postService.saveUserNewPost(Integer.valueOf(nodeId),user.getId(),title,content);
            System.out.println(post);
            //将存现成功的对象返还给客户端
            jsonResult = new JsonResult(post);
        }catch (ServiceException ex){
           jsonResult = new JsonResult(ex.getMessage());

        }
        readJson(jsonResult,resp);

    }
}
