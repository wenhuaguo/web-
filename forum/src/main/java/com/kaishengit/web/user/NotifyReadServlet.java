package com.kaishengit.web.user;

import com.kaishengit.service.RegService;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/27.
 */
@WebServlet("/notifyRead")
public class NotifyReadServlet extends SendHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        System.out.println(ids);
        RegService regService = new RegService();
        regService.updateNotifyStateById(ids);
        readText("success",resp);
    }
}
