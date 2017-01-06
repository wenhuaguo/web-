package com.kaishengit.web.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/29.
 */
@WebServlet("/admin/newnode")
public class NewNodeServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("admin/newnode",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newNode = req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        try {
            nodeService.addNewNode(newNode);
            readText("success",resp);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
