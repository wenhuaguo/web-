package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.NodeService;

import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/28.
 */
@WebServlet("/admin/editNode")
public class EditNodeServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeId = req.getParameter("nodeId");

        NodeService nodeService = new NodeService();

        Node node = nodeService.findNodeByNodeid(nodeId);
        req.setAttribute("node",node);
        forward("admin/editNode",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeName = req.getParameter("nodename");
        String nodeId = req.getParameter("nodeid");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult = new JsonResult();
        try {
            nodeService.updateNodeName(nodeId,nodeName);
            jsonResult.setState("success");
        }catch (ServiceException ex){
            jsonResult.setMessage(ex.getMessage());
        }
        readJson(jsonResult,resp);
    }
}
