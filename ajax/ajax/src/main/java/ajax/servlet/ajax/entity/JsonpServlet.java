package ajax.servlet.ajax.entity;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 2016/12/8.
 */
@WebServlet("/jsonp")
public class JsonpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");//获得地址栏的值
        System.out.println(method);

        User user1 = new User(1001,"王五","天津");
        User user2 = new User(1002,"李四","石家庄");
        List<User> userList = Arrays.asList(user1,user2);//将两个对象同时添加到集合当中去
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");//jsonp的话需要返回text/html
        String json = new Gson().toJson(userList);

        PrintWriter out = resp.getWriter();
        //out.print(json);
        out.print(method + "("+json+")");//将函数执行加()，里面可以添加内容
        out.flush();
        out.close();
    }
}
