package com.kaishengit.web;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Post;
import com.kaishengit.service.PostService;
import com.kaishengit.util.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/15.
 */
@WebServlet("/home")
public class indexServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.首先将节点返回给页面
        PostService postService = new PostService();
        List<Node> nodeList = postService.readNode();
        req.setAttribute("nodeList",nodeList);
        //进行分页显示功能
        //1.首先不分类别显示
        String p = req.getParameter("p");
        System.out.println("p:" + p);
        String nodeid = req.getParameter("nodeid");
        System.out.println("nodeId" + nodeid);
        //2.利用三元表达式判断p的值(如果不为数字那么就让它去第一页)
        Integer pageNo = StringUtils.isNumeric(p) ? Integer.valueOf(p):1;
        System.out.println(pageNo);
        if(!StringUtils.isEmpty(nodeid) && !StringUtils.isNumeric(nodeid)) {
            forward("index",req,resp);
            return;
        }

        Page<Post> postList = postService.findPostByPageNo(pageNo,nodeid);
        System.out.println("postList:" + postList.getItems());
        req.setAttribute("pageList",postList);
        forward("index",req,resp);
    }
}
