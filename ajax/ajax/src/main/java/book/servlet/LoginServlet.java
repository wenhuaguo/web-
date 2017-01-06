package book.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Acer on 2016/12/8.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        System.out.println(userName + password);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");//返回json数据
        Map<String,Object> result = new HashMap<>();
        PrintWriter out = resp.getWriter();
        if("tom".equals(userName) && "123123".equals(password)){
            result.put("state","success");
        }else {
            result.put("state","error");
            result.put("message","账号或密码错误");
        }
        String json = new Gson().toJson(result);//链式调用

        out.print(json);
        out.flush();
        out.close();
    }
}
