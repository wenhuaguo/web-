package com.kaishengit.web.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.util.ChangeEcoding;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/29.
 */
@WebServlet("/admin/nodeNameValidate")
public class NodeNameValidate extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NodeService nodeService = new NodeService();
        String nodeName = req.getParameter("nodename");
        nodeName = ChangeEcoding.chageEncoding(nodeName);
            Boolean result = nodeService.findNodeByNodeName(nodeName);
            if(result){
                readText("true",resp);
            }else {
                readText("false",resp);
            }
    }
}
