package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.PostService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/28.
 */
@WebServlet("/admin/node")
public class NodeServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将所有节点查询出来给客户端做出响应
        PostService postService = new PostService();
        List<Node> nodeList = postService.readNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/nodeManage",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid = req.getParameter("nodeid");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult = new JsonResult();
        try {
            nodeService.deleteNodeByNodeId(nodeid);
            jsonResult.setState("success");
        }catch (ServiceException ex){
            jsonResult.setMessage(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
