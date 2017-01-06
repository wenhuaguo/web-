package com.kaishengit.web.user;

import com.kaishengit.service.RegService;
import com.kaishengit.util.ChangeEcoding;
import com.kaishengit.web.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Acer on 2016/12/16.
 */
@WebServlet("/user/validate/username")
public class ValidataUserNameServelt extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        System.out.println(userName);
        userName = ChangeEcoding.chageEncoding(userName);

        RegService reg = new RegService();
        Boolean result = reg.findByName(userName);
        if(result){
            readText("true",resp);
        }else {
            readText("false",resp);
        }
    }
}
